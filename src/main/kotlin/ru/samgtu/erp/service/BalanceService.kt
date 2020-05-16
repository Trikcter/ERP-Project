package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.Balance
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.BalanceRepository
import java.math.BigDecimal

@Service
class BalanceService {
    @Autowired
    private lateinit var balanceRepository: BalanceRepository

    @Transactional
    fun createBalance(organization: Organization): Balance {
        val balance = Balance(0, BigDecimal.ZERO, organization)
        return balanceRepository.save(balance)
    }

    @Transactional
    fun save(balance: Balance): Balance {
        return balanceRepository.save(balance)
    }
}