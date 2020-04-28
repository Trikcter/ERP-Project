package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY, targetEntity = Balance::class)
        @JoinColumn(name = "balance_id")
        var balance: Balance,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        var product: Product,

        @Column(name = "count")
        var count: Long
)