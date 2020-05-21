package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.exception.ERPException
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.model.WarehouseCondition
import ru.samgtu.erp.model.WarehouseOperation
import ru.samgtu.erp.repository.AddressRepository
import ru.samgtu.erp.repository.WarehouseConditionRepository
import ru.samgtu.erp.repository.WarehouseOperationRepository
import ru.samgtu.erp.repository.WarehouseRepository
import java.time.LocalDateTime

@Service
class WarehouseService : CrudService<Warehouse>() {
    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var typeOfWarehouseOperationService: TypeOfWarehouseOperationService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var warehouseOperationRepository: WarehouseOperationRepository

    @Autowired
    private lateinit var warehouseConditionRepository: WarehouseConditionRepository

    @Transactional
    fun saveCondition(warehouseCondition: WarehouseCondition,
                      typeOperationId: Long,
                      warehouseId: Long?): ResponseEntity<*> {
        val product = productService.getById(warehouseCondition.product.id)
        val typeOperation = typeOfWarehouseOperationService.getById(typeOperationId)

        val currentWarehouse = this.getById(warehouseCondition.warehouse.id)

        val condition = warehouseConditionRepository.findFirstByProductAndWarehouse(product, currentWarehouse)
                .orElse(WarehouseCondition(0, 0))
        val futureCount = condition.count + warehouseCondition.count

        if (futureCount < 0) {
            throw ERPException("На складе не достаточно товаров данного типа")
        }

        if (futureCount > currentWarehouse.volume) {
            throw ERPException("На складе нет места")
        }

        condition.count += warehouseCondition.count

        if (condition.id == 0L) {
            condition.product = product
            condition.warehouse = currentWarehouse
        }

        if (warehouseId != null) {
            val negativeCount = warehouseCondition.count * -1
            val anotherWarehouse = this.getById(warehouseId)
            val anotherConditionOptional = warehouseConditionRepository
                    .findFirstByProductAndWarehouse(product, anotherWarehouse)

            if (!anotherConditionOptional.isPresent && negativeCount > 0) {
                throw ERPException("На втором складе нет данной продукции")
            }

            val anotherCondition = anotherConditionOptional.get()

            val anotherFutureCount = anotherCondition.count + negativeCount

            if (anotherFutureCount < 0) {
                throw ERPException("На втором складе не достаточно товаров данного типа")
            }

            if (anotherFutureCount > anotherWarehouse.volume) {
                throw ERPException("На втором складе нет места")
            }

            anotherCondition.count += warehouseCondition.count

            if (anotherCondition.id == 0L) {
                anotherCondition.product = product
                anotherCondition.warehouse = anotherWarehouse
            }

            val anotherOperation = WarehouseOperation(
                    0,
                    warehouseCondition.count,
                    LocalDateTime.now(),
                    typeOperation,
                    currentWarehouse,
                    product
            )

            warehouseConditionRepository.save(anotherCondition)
            warehouseOperationRepository.save(anotherOperation)
        }

        val operation = WarehouseOperation(
                0,
                warehouseCondition.count,
                LocalDateTime.now(),
                typeOperation,
                currentWarehouse,
                product
        )

        warehouseConditionRepository.save(condition)
        warehouseOperationRepository.save(operation)

        return ResponseEntity.ok("Операция сохранена!")
    }

    @Transactional
    override fun save(entity: Warehouse): Warehouse {
        var address = addressRepository
                .findByTitle(entity.address.title)
                .orElseGet { Address(entity.address.title) }

        if (address.id == 0L) {
            address = addressRepository.save(entity.address)
        }

        entity.address = address
        entity.organization = organizationService.getById(entity.organization.id)

        return warehouseRepository.save(entity)
    }

    fun getAllById(id: Long, pageable: Pageable): Page<Warehouse> {
        val organization = organizationService.getById(id)

        return warehouseRepository.findAllByOrganization(organization, pageable)
    }

    fun getAllActiveById(id: Long, pageable: Pageable): Page<Warehouse> {
        val organization = organizationService.getById(id)

        return warehouseRepository.findAllByOrganizationAndIsDeletedIsFalse(organization, pageable)
    }

    fun saveAll(warehouses: List<Warehouse>): ResponseEntity<*> {
        warehouses.forEach {
            this.save(it)
        }

        return ResponseEntity.ok("Все склады добавлены!")
    }

    override fun getRepository(): JpaRepository<Warehouse, Long> {
        return warehouseRepository
    }
}