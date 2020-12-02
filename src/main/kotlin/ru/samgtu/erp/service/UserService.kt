package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.User
import ru.samgtu.erp.repository.RoleRepository
import ru.samgtu.erp.repository.UserRepository
import ru.samgtu.erp.utils.SecurityUtils
import javax.persistence.EntityNotFoundException

/**
 * Сервис для работы с сущностью User
 */
@Service
class UserService : CrudService<User>() {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    fun saveWorker(id: Long, user: User) {
        val organization = organizationService.getById(id)
        val role = roleRepository.findByName(ArrayList(user.roles!!)[0].name)

        user.organization = organization
        user.roles = listOf(role)

        if (user.id == 0L) {
            user.password = encoder.encode(user.password)
        }

        this.save(user)
    }

    override fun getRepository(): JpaRepository<User, Long> {
        return userRepository
    }

    fun getAllByOrganizationId(id: Long, pageable: Pageable): Page<User> {
        val organization = organizationService.getById(id)

        return userRepository.findAllByOrganization(organization, pageable)
    }

    /**
     * Получение текущего principal объекта
     */
    fun getCurrentUser(): User {
        val username = SecurityUtils.getUserName()

        return userRepository.findByLogin(username)
            .orElseThrow { throw EntityNotFoundException() }
    }
}