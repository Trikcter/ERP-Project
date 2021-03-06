package ru.samgtu.erp.model

import javax.persistence.*

/**
 * Состояние склада, представление в виде ассоциативного массива Товар -> Кол-во
 */
@Entity
@Table(name = "warehouse_condition")
data class WarehouseCondition(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "count", nullable = false)
    var count: Long
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    lateinit var product: Product

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    lateinit var warehouse: Warehouse
}