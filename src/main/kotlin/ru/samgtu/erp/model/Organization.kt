package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "organization")
data class Organization(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0,

        @Column(name = "inn", nullable = false)
        var inn: String,

        @Column(name = "kpp", nullable = false)
        var kpp: String,

        @Column(name = "ogrn", nullable = false)
        var ogrn: String,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "is_deleted", nullable = false)
        override var isDeleted: Boolean
) : AbstractEntity {
    constructor(id: Long) : this(id, "", "", "", "", false)

    @Column(name = "url", nullable = true)
    var url: String = ""

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, referencedColumnName = "id")
    lateinit var address: Address

    @OneToMany(fetch = FetchType.LAZY)
    var staff: Collection<User>? = null

    @OneToMany(fetch = FetchType.LAZY)
    var goods: Collection<Product>? = null

    @OneToMany(fetch = FetchType.LAZY)
    var warehouses: Collection<Warehouse>? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_id", referencedColumnName = "id")
    lateinit var balance: Balance
}