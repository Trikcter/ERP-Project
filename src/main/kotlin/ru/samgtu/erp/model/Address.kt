package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "is_deleted", nullable = false)
    override var isDeleted: Boolean
) : AbstractEntity {
    constructor(title: String) : this(0L, title, false)
}