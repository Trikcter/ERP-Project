package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.User
import ru.samgtu.erp.repository.UserRepository
import ru.samgtu.erp.utils.SecurityUtils
import javax.persistence.EntityNotFoundException

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun getCurrentUserId(): Long? {
        val username = SecurityUtils.getUserName()

        return userRepository.findByLogin(username)
                .orElseThrow { throw EntityNotFoundException() }
                .id
    }

    fun getCurrentUser(): User {
        val username = SecurityUtils.getUserName()

        return userRepository.findByLogin(username)
                .orElseThrow { throw EntityNotFoundException() }
    }
}