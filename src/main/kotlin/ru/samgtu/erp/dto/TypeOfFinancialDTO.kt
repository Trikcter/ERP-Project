package ru.samgtu.erp.dto

data class TypeOfFinancialDTO(
        var id: Long,

        var description: String?,

        var name: String,

        var organizationId: Long,

        var isDeleted: Boolean
)