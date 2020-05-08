package ru.samgtu.erp.mapper

import ru.samgtu.erp.model.AbstractEntity

/**
 * Задает интерфейс для мапперов в DTO и обратно
 *
 * X - сущность DTO
 * T - сущности модели
 */
interface CrudMapper<X, T : AbstractEntity> {
    fun dto2model(dto: X): T

    fun model2dto(model: T): X
}