package ru.samgtu.erp.dto

data class TypeOfWarehouseDTO(
        var id: Long,

        var description: String?,

        var name: String,

        var organizationId: Long,

        var isDeleted: Boolean
)