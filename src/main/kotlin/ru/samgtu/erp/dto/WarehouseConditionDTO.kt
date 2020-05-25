package ru.samgtu.erp.dto

import javax.validation.constraints.NotNull

data class WarehouseConditionDTO(
        val id: Long,

        @NotNull
        val count: Long,

        @NotNull
        val product: ProductDTO,

        @NotNull
        val warehouseId: Long
)