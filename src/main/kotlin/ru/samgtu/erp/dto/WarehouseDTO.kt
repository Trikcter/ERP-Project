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

        @NotNull
        var volume: Long,

        var isDeleted: Boolean
) {
        var condition: List<WarehouseConditionDTO>? = null
}