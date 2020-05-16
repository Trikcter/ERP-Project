package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.repository.AddressRepository
import ru.samgtu.erp.repository.OrganizationRepository
import ru.samgtu.erp.repository.WarehouseRepository
import javax.persistence.EntityNotFoundException

@Service
class WarehouseService : CrudService<Warehouse>() {
    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var organizationRepository: OrganizationRepository

    override fun save(entity: Warehouse): Warehouse {
        var address = addressRepository
                .findByTitle(entity.address.title)
                .orElseGet { Address(entity.address.title) }

        if (address.id == 0L) {
            address = addressRepository.save(entity.address)
        }

        entity.address = address
        entity.organization = organizationRepository
                .findById(entity.organization.id)
                .orElseThrow { throw EntityNotFoundException() }

        return warehouseRepository.save(entity)
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