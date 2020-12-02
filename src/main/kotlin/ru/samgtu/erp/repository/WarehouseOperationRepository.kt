package ru.samgtu.erp.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.WarehouseOperation

@Repository
interface WarehouseOperationRepository : JpaRepository<WarehouseOperation, Long> {
    @Query(
        value = "SELECT warehouseOperation FROM WarehouseOperation warehouseOperation " +
                "INNER JOIN warehouseOperation.warehouse warehouse " +
                "INNER JOIN warehouse.organization organization " +
                "WHERE organization.id = :id"
    )
    fun findAllByOrganization(
        @Param("id") id: Long,
        pageable: Pageable
    ): Page<WarehouseOperation>

    @Query(
        value = "SELECT warehouseOperation FROM WarehouseOperation warehouseOperation " +
                "INNER JOIN warehouseOperation.warehouse warehouse " +
                "INNER JOIN warehouse.organization organization " +
                "WHERE organization.id = :organizationId " +
                "AND warehouse.id = :warehouseId"
    )
    fun findAllByOrganizationAndWarehouse(
        @Param("organizationId") organizationId: Long,
        @Param("warehouseId") warehouseId: Long,
        pageable: Pageable
    ): Page<WarehouseOperation>
}