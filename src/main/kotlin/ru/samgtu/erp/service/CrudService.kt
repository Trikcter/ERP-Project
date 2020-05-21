package ru.samgtu.erp.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.AbstractEntity
import javax.persistence.EntityNotFoundException

abstract class CrudService<T : AbstractEntity> {
    abstract fun getRepository(): JpaRepository<T, Long>

    @Transactional
    open fun save(entity: T): T {
        return getRepository().save(entity)
    }

    open fun getEntityById(id: Long): T {
        return getRepository().findById(id)
                .orElseThrow { throw EntityNotFoundException() }
    }

    open fun getAll(pageable: Pageable): Page<T> {
        return getRepository().findAll(pageable)
    }

    fun getById(id: Long?): T {
        val entityId = id ?: throw EntityNotFoundException()

        return getRepository().findById(entityId).orElseThrow { throw EntityNotFoundException() }
    }

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