package ru.samgtu.erp.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "balance_registry")
data class BalanceRegistry(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "value")
        val value: BigDecimal,

        @Column(name = "date")
        val date: LocalDateTime,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "balance_id", referencedColumnName = "id")
        var balance: Balance
)