package ru.samgtu.erp.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.Product

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByOrganization(organization: Organization, pageable: Pageable): Page<Product>
}