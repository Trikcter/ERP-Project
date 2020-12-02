package ru.samgtu.erp.dto

data class ChartDTO<T>(
    val labels: List<String>,
    val values: List<T>
)