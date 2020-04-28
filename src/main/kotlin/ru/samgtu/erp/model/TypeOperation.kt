package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "type_operation")
data class TypeOperation(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "description")
        var description: String?,

        @Column(name = "name", nullable = false)
        var name: String
)