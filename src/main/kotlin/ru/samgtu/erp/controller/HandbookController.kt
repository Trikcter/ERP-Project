package ru.samgtu.erp.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.samgtu.erp.model.TypeOfOperation
import ru.samgtu.erp.service.HandbookService

abstract class HandbookController<X, T : TypeOfOperation> : CrudController<X, T>() {
    abstract override fun getService(): HandbookService<T>

    @GetMapping("/all/{id}")
    fun getAllById(@PathVariable id: Long, pageable: Pageable): Page<X> {
        return getService().getAllById(id, pageable).map { getMapper().model2dto(it) }
    }

    @GetMapping("/active/{id}")
    fun getAllActiveByOrganization(@PathVariable id: Long, pageable: Pageable): Page<X> {
        return getService().getAllActiveById(id, pageable)
                .map { entity -> getMapper().model2dto(entity) }
    }
}