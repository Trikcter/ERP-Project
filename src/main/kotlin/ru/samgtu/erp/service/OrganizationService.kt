package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.OrganizationRepository
import javax.persistence.EntityNotFoundException

@Service
class OrganizationService {
    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    fun save(organization: Organization): Organization {
        return organizationRepository.save(organization)
    }

    fun delete(ids: List<Long>) {
        val organizations = ids.map {
            val organization = organizationRepository.findById(it).orElseThrow { throw EntityNotFoundException() }
            organization.isDeleted = true

            organization
        }

        organizationRepository.saveAll(organizations)
    }

    fun getOrganization(id: Long): Organization {
        return organizationRepository.findById(id).orElseThrow { throw EntityNotFoundException() }
    }

    fun getAll(page: Pageable): Page<Organization> {
        return organizationRepository.findAll(page)
    }
}