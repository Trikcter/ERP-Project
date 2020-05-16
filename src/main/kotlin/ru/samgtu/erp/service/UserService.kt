package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.User
import ru.samgtu.erp.repository.UserRepository
import ru.samgtu.erp.utils.SecurityUtils
import javax.persistence.EntityNotFoundException

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getCurrentUserId(): Long? {
        val username = SecurityUtils.getUserName()

        return userRepository.findByLogin(username)
                .orElseThrow { throw EntityNotFoundException() }
                .id
    }

    @Transactional
    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getCurrentUser(): User {
        val username = SecurityUtils.getUserName()

        return userRepository.findByLogin(username)
                .orElseThrow { throw EntityNotFoundException() }
    }
}