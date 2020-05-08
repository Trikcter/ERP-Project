package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long?,

        @Column(name = "count")
        var count: Long
) {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Balance::class)
    @JoinColumn(name = "balance_id")
    lateinit var balance: Balance

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    lateinit var product: Product
}