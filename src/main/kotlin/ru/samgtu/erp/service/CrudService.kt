package ru.samgtu.erp.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.AbstractEntity
import javax.persistence.EntityNotFoundException

abstract class CrudService<T : AbstractEntity> {
    /**
     * Получение репозитория для работы с данными
     */
    abstract fun getRepository(): JpaRepository<T, Long>

    /**
     * Сохранение сущности
     *
     * @param entity - сущность
     */
    @Transactional
    open fun save(entity: T): T {
        return getRepository().save(entity)
    }

    /**
     * Получение всего списка сущности
     *
     * @param pageable - пагинация
     */
    open fun getAll(pageable: Pageable): Page<T> {
        return getRepository().findAll(pageable)
    }

    /**
     * Получение сущности по ID
     *
     * @param id - ID сущности
     */
    fun getById(id: Long?): T {
        val entityId = id ?: throw EntityNotFoundException()

        return getRepository().findById(entityId).orElseThrow { throw EntityNotFoundException() }
    }

    /**
     * Удаление списка сущностей
     *
     * @param ids - список ID сущностей
     */
    @Transactional
    open fun delete(ids: List<Long>) {
        val entities = ids.map {
            val entity = getRepository().findById(it).orElseThrow { throw EntityNotFoundException() }
            entity.isDeleted = true

            entity
        }

        getRepository().saveAll(entities)
    }
}