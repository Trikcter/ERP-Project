package ru.samgtu.erp.dto

data class ErrorDTO(
    val message: String,
    val path: String,
    val status: String
)