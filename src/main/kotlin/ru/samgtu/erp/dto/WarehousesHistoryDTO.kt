package ru.samgtu.erp.dto

import java.time.LocalDateTime

data class WarehousesHistoryDTO(
        val count: Long,
        val date: LocalDateTime,
        val warehouseTitle: String,
        val productTitle: String,
        val typeTitle: String
)