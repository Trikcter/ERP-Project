package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.repository.AddressRepository
import ru.samgtu.erp.repository.WarehouseRepository

@Service
class WarehouseService : CrudService<Warehouse>() {
    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

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