package ru.samgtu.erp.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "balance")
data class Balance(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "all_balance", nullable = false)
        var allBalance: BigDecimal
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "balance")
    var orderOperations: Collection<Order>? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "balance")
    var bankOperations: Collection<BankOperation>? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false, referencedColumnName = "id")
    lateinit var organization: Organization
}