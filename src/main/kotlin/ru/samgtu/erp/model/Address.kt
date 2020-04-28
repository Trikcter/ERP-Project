package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "is_deleted", nullable = false)
        var isDeleted: Boolean
)