package ru.samgtu.erp.dto

import java.math.BigDecimal
import javax.validation.constraints.NotNull

data class ProductDTO(
        var id: Long,

        @NotNull
        var title: String,

        var code: String?,

        @NotNull
        var price: BigDecimal,

        var organizationId: Long,

        var isDeleted: Boolean
)