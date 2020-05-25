package ru.samgtu.erp.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.samgtu.erp.model.Role

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role
}