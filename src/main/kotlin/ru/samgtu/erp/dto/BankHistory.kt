package ru.samgtu.erp.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class BankHistory(
    val sum: BigDecimal,
    val comment: String?,
    val date: LocalDateTime,
    val typeTitle: String
)