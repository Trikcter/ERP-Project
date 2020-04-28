package ru.samgtu.erp.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "code")
        var code: String?,

        @Column(name = "price", nullable = false)
        var price: BigDecimal,

        @Column(name = "is_deleted", nullable = false)
        var isDeleted: Boolean
)