package ru.samgtu.erp.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "bank_operation")
data class BankOperation(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @ManyToOne(fetch = FetchType.LAZY, targetEntity = Balance::class)
        @JoinColumn(name = "balance_id")
        var balance: Balance,

        @Column(name = "sum", nullable = false)
        var sum: BigDecimal,

        @Column(name = "comment")
        var comment: String?,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
        var typeOperation: TypeOperation
)