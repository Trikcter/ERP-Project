package ru.samgtu.erp.dto

import javax.validation.constraints.NotNull

data class WarehouseDTO(
        var id: Long,

        @NotNull
        var title: String,

        @NotNull
        var organizationId: Long,

        @NotNull
        var addressTitle: String,

        var isDeleted: Boolean
)