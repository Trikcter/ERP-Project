package ru.samgtu.erp.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "bank_operation")
data class BankOperation(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "sum", nullable = false)
        var sum: BigDecimal,

        @Column(name = "comment")
        var comment: String?
) {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    lateinit var typeOperation: TypeOperation

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Balance::class)
    @JoinColumn(name = "balance_id")
    lateinit var balance: Balance
}