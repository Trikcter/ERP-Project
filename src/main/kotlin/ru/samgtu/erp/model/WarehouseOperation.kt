package ru.samgtu.erp.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "warehouse_operation")
data class WarehouseOperation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "count", nullable = false)
    var count: Long,

    @Column(name = "date_of_operation", nullable = false)
    var dateOfOperation: LocalDateTime,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    var typeOfWarehouseOperation: TypeOfWarehouseOperation,

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Warehouse::class)
    @JoinColumn(name = "warehouse_id")
    var warehouse: Warehouse,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product
)