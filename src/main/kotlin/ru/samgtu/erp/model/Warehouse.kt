package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "warehouse")
data class Warehouse(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
        var address: Address?,

        @Column(name = "title", nullable = false)
        var title: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organization_id", nullable = false)
        var organization: Organization,

        @Column(name = "is_deleted", nullable = false)
        var isDeleted: Boolean,

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
        var conditions: Collection<WarehouseCondition>?
)