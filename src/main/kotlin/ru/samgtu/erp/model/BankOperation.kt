package ru.samgtu.erp.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "bank_operation")
data class BankOperation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "sum", nullable = false)
        var sum: BigDecimal,

        @Column(name = "comment")
        var comment: String?,

        @Column(name = "date_of_operation", nullable = false)
        var dateOfOperation: LocalDateTime
) {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    lateinit var typeOfFinancialOperation: TypeOfFinancialOperation

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Balance::class)
    @JoinColumn(name = "balance_id")
    lateinit var balance: Balance
}