package ru.samgtu.erp.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.model.WarehouseCondition
import java.util.*

interface WarehouseConditionRepository : JpaRepository<WarehouseCondition, Long> {
    fun findFirstByProductAndWarehouse(product: Product, warehouse: Warehouse): Optional<WarehouseCondition>

    fun findAllByWarehouse(warehouse: Warehouse, pageable: Pageable): Page<WarehouseCondition>
}