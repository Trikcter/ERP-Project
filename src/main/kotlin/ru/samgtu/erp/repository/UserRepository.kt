package ru.samgtu.erp.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(@Param("login") username: String): Optional<User>
}