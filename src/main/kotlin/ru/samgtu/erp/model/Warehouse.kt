package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "warehouse")
data class Warehouse(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override var id: Long?,

        @Column(name = "title", nullable = false)
        var title: String,

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