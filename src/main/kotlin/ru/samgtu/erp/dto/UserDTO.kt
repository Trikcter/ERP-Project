package ru.samgtu.erp.dto

data class UserDTO(
        val id: Long,
        val login: String,
        val password: String,
        val fio: String,
        val role: RoleDTO,
        val isDeleted: Boolean
)