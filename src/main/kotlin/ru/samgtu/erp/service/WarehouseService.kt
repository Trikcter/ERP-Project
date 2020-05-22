package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.model.WarehouseCondition
import ru.samgtu.erp.repository.AddressRepository
import ru.samgtu.erp.repository.WarehouseConditionRepository
import ru.samgtu.erp.repository.WarehouseRepository

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
    private lateinit var warehouseConditionService: WarehouseConditionService

    @Autowired
    private lateinit var warehouseConditionRepository: WarehouseConditionRepository

    @Autowired
    private lateinit var warehouseValidService: WarehouseValidService

    /**
     * Сохранение нового состояния склада
     *
     * @param warehouseCondition - новое состояние
     * @param typeOperationId - ID типа операции, происходящей на склада
     * @param warehouseId - ID второго склада, участвующего в операции
     */
    @Transactional
    fun saveCondition(warehouseCondition: WarehouseCondition,
                      typeOperationId: Long,
                      warehouseId: Long?): ResponseEntity<*> {
        val product = productService.getById(warehouseCondition.product.id)
        val typeOperation = typeOfWarehouseOperationService.getById(typeOperationId)
        val currentWarehouse = this.getById(warehouseCondition.warehouse.id)

        warehouseValidService.validate(product, currentWarehouse, warehouseCondition.count)

        warehouseConditionService.createCondition(warehouseCondition.count, product, currentWarehouse, typeOperation)

        if (warehouseId != null) {
            val negativeCount = warehouseCondition.count * -1
            val secondWarehouse = this.getById(warehouseId)

            warehouseValidService.validate(product, secondWarehouse, negativeCount)

            warehouseConditionService.createCondition(negativeCount, product, secondWarehouse, typeOperation)
        }

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

    /**
     * Получение всех складов организации
     *
     * @param id - ID организации
     * @param pageable - пагинация
     */
    fun getAllById(id: Long, pageable: Pageable): Page<Warehouse> {
        val organization = organizationService.getById(id)

        val warehouses = warehouseRepository.findAllByOrganization(organization, pageable)
        warehouses.forEach {
            run {
                val congestion = this.getCongestion(it.volume, it.conditions as List<WarehouseCondition>)
                it.congestion = congestion
            }
        }

        return warehouses
    }

    /**
     * Получение информации о состоянии склада
     *
     * @param id - ID склада
     * @param pageable - пагинация
     */
    fun getConditionsById(id: Long, pageable: Pageable): Page<WarehouseCondition> {
        val warehouse = this.getById(id)

        return warehouseConditionRepository.findAllByWarehouse(warehouse, pageable)
    }

    /**
     * Получение всех активных складов организации
     *
     * @param id - ID организации
     * @param pageable - пагинация
     */
    fun getAllActiveById(id: Long, pageable: Pageable): Page<Warehouse> {
        val organization = organizationService.getById(id)

        return warehouseRepository.findAllByOrganizationAndIsDeletedIsFalse(organization, pageable)
    }

    /**
     * Сохранение всех складов, добавляемых для организаци
     * @param warehouses - список складов
     */
    fun saveAll(warehouses: List<Warehouse>): ResponseEntity<*> {
        warehouses.forEach {
            this.save(it)
        }

        return ResponseEntity.ok("Все склады добавлены!")
    }

    override fun getRepository(): JpaRepository<Warehouse, Long> {
        return warehouseRepository
    }

    /**
     * Рассчет заполненности склада
     * @param volume - объем склада
     * @param conditions - состояние склада
     */
    private fun getCongestion(volume: Long, conditions: List<WarehouseCondition>): Double {
        val count = conditions
                .map { it.count }
                .sum()

        return (count.toDouble() / volume.toDouble()) * 100
    }
}