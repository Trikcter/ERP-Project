package ru.samgtu.erp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.Warehouse

@Repository
interface WarehouseRepository : JpaRepository<Warehouse, Long> {
}