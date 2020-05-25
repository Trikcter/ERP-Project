package ru.samgtu.erp.model

interface AbstractEntity {
    var id: Long
    var isDeleted: Boolean
}