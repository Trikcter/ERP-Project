package ru.samgtu.erp.model

/**
 * Интерфейс описывает операции, происходящие с сущностями в бизнесе
 */
interface TypeOfOperation : AbstractEntity {
    var description: String?

    var name: String

    var organization: Organization
}