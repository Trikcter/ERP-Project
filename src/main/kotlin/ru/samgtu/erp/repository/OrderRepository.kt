package ru.samgtu.erp.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.Order

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    @Query(
        value = "SELECT ord FROM Order ord " +
                "INNER JOIN ord.balance balance " +
                "INNER JOIN balance.organization organization " +
                "WHERE organization.id = :id"
    )
    fun findAllByOrganizationId(@Param("id") organizationId: Long, pageable: Pageable): Page<Order>
}