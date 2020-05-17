package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "warehouse")
data class Warehouse(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "volume", nullable = false)
        var volume: Long,

        @Column(name = "is_deleted", nullable = false)
        override var isDeleted: Boolean
) : AbstractEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
    lateinit var address: Address

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    var conditions: Collection<WarehouseCondition>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    lateinit var organization: Organization
}