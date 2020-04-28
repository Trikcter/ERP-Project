package ru.samgtu.erp.model

import javax.persistence.*

@Entity
@Table(name = "organization")
data class Organization(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "inn", nullable = false)
        var inn: String,

        @Column(name = "kpp", nullable = false)
        var kpp: String,

        @Column(name = "ogrn", nullable = false)
        var ogrn: String,

        @Column(name = "title", nullable = false)
        var title: String,

        // TODO: Переделать сохранение изображения
        /*@Column(name = "logo")
        var logo: String? = null,*/

        @Column(name = "is_deleted", nullable = false)
        var isDeleted: Boolean,

        @OneToMany(fetch = FetchType.LAZY)
        var staff: Collection<User>? = null,

        @OneToMany(fetch = FetchType.LAZY)
        var goods: Collection<Product>? = null,

        @OneToMany(fetch = FetchType.LAZY)
        var warehouses: Collection<Warehouse>,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "balance_id", referencedColumnName = "id")
        var balance: Balance
)