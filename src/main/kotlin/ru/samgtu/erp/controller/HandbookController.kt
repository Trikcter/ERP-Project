package ru.samgtu.erp.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.samgtu.erp.model.TypeOfOperation
import ru.samgtu.erp.service.HandbookService

abstract class HandbookController<X, T : TypeOfOperation> : CrudController<X, T>() {
    abstract override fun getService(): HandbookService<T>

    @GetMapping("/all")
    fun getAllById(@RequestParam organizationId: Long, pageable: Pageable): Page<X> {
        return getService().getAllById(organizationId, pageable).map { getMapper().model2dto(it) }
    }

    @GetMapping("/all/active")
    fun getAllActiveByOrganization(@RequestParam organizationId: Long, pageable: Pageable): Page<X> {
        return getService().getAllActiveById(organizationId, pageable)
                .map { entity -> getMapper().model2dto(entity) }
    }
}