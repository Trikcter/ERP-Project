package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.OrganizationRepository
import javax.persistence.EntityNotFoundException

@Service
class OrganizationService : CrudService<Organization>() {
    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    @Autowired
    lateinit var balanceService: BalanceService

    @Autowired
    lateinit var userService: UserService

    override fun save(entity: Organization): Organization {
        val saved = organizationRepository.save(entity)

        return if (entity.id == 0L) {
            val balance = balanceService.createBalance(saved)
            saved.balance = balance
            saved.owner = userService.getCurrentUser()

            organizationRepository.save(saved)
        } else {
            saved
        }
    }

    fun getById(id: Long?): Organization {
        val entityId = id ?: throw EntityNotFoundException()

        return organizationRepository.findById(entityId).orElseThrow { throw EntityNotFoundException() }
    }

    override fun getRepository(): JpaRepository<Organization, Long> {
        return organizationRepository
    }
}