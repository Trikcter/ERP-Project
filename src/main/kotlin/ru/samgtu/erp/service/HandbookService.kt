package ru.samgtu.erp.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.TypeOfOperation
import ru.samgtu.erp.repository.HandbookRepository

/**
 * Сервис для работы со справочниками
 */
abstract class HandbookService<T : TypeOfOperation> : CrudService<T>() {
    abstract fun getOrganizationService(): CrudService<Organization>

    abstract override fun getRepository(): HandbookRepository<T>

    /**
     *  Получение всех доступных значений справочника по ID организации
     *
     *  @param id - ID организации
     */
    fun getAllById(id: Long, pageable: Pageable): Page<T> {
        val organization = getOrganizationService().getById(id)

        return getRepository().findAllByOrganization(organization, pageable)
    }

    /**
     * Получение все доступных, активных значений справочника по ID организации
     *
     * @param id - ID организации
     */
    fun getAllActiveById(id: Long, pageable: Pageable): Page<T> {
        val organization = getOrganizationService().getById(id)

        return getRepository().findAllByOrganizationAndIsDeletedIsFalse(organization, pageable)
    }
}