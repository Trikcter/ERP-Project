package ru.samgtu.erp.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import ru.samgtu.erp.model.AbstractEntity
import ru.samgtu.erp.model.Organization

@NoRepositoryBean
interface HandbookRepository<T : AbstractEntity> : JpaRepository<T, Long> {
    fun findAllByOrganization(organization: Organization, pageable: Pageable): Page<T>

    fun findAllByOrganizationAndIsDeletedIsFalse(organization: Organization, pageable: Pageable): Page<T>
}