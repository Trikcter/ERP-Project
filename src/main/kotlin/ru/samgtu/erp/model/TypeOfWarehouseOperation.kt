package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "warehouse_type_operation")
data class TypeOfWarehouseOperation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,

        @Column(name = "description")
        override var description: String?,

        @Column(name = "name", nullable = false)
        override var name: String,

        @Column(name = "is_deleted", nullable = false)
        override var isDeleted: Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organization_id", nullable = false)
        override var organization: Organization
) : TypeOfOperation