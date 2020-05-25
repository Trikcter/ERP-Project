package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name", nullable = false)
        val name: String,

        @Column(name = "description", nullable = false)
        val description: String
)