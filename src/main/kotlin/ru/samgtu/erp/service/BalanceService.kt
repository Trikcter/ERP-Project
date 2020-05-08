package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Balance
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.BalanceRepository
import java.math.BigDecimal

@Service
class BalanceService {
    @Autowired
    lateinit var balanceRepository: BalanceRepository

    fun createBalance(organization: Organization): Balance {
        val balance = Balance(null, BigDecimal.ZERO, organization)
        return balanceRepository.save(balance)
    }

    fun save(balance: Balance): Balance {
        return balanceRepository.save(balance)
    }
}