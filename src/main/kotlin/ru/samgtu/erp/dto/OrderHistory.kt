package ru.samgtu.erp.dto

import java.time.LocalDateTime

data class OrderHistory(
        val count: Long,
        val productTitle: String,
        val date: LocalDateTime
)