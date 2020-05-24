package ru.samgtu.erp.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.model.AbstractEntity
import ru.samgtu.erp.service.CrudService

/**
 * Класс, описывающий стандартные CRUD-операции над сущностью
 *
 * X - типа DTO
 * T - тип сущности
 *
 */
abstract class CrudController<X, T : AbstractEntity> {
    /**
     * Функция, возвращающая маппер для маппинга сущностей
     */
    abstract fun getMapper(): CrudMapper<X, T>

    abstract fun getService(): CrudService<T>

    @GetMapping("{id}")
    fun getEntityById(@PathVariable id: Long): X {
        return getMapper().model2dto(getService().getById(id))
    }

    @GetMapping
    fun getAllEntities(page: Pageable): Page<X> {
        return getService().getAll(page)
                .map { entity -> getMapper().model2dto(entity) }
    }

    @PostMapping
    fun addEntity(@RequestBody dto: X): X {
        return getMapper().model2dto(
                getService().save(getMapper().dto2model(dto))
        )
    }

    @PutMapping
    fun editEntity(@RequestBody dto: X): X {
        return getMapper().model2dto(
                getService().save(getMapper().dto2model(dto))
        )
    }

    @DeleteMapping
    fun deleteByIds(@RequestBody ids: List<Long>): ResponseEntity<Any> {
        getService().delete(ids)
        return ResponseEntity.ok().build()
    }
}