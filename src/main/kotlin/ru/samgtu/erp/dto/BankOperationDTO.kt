package ru.samgtu.erp.dto

import java.math.BigDecimal

data class BankOperationDTO(
    val sum: BigDecimal,
    val comment: String?,
    val typeId: Long
)