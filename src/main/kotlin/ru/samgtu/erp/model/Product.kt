package ru.samgtu.erp.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "code")
        var code: String?,

        @Column(name = "price", nullable = false)
        var price: BigDecimal,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organization_id", referencedColumnName = "id")
        var organization: Organization,

        @Column(name = "is_deleted", nullable = false)
        override var isDeleted: Boolean
) : AbstractEntity {
        constructor(id: Long) : this(id, "", "", BigDecimal.ZERO, Organization(0L), false)
}
