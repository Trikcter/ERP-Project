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

    @Autowired
    private lateinit var balanceRegistryService: BalanceRegistryService

    /**
     * Создание баланса организации
     *
     * @param organization - организация
     */
    @Transactional
    fun createBalance(organization: Organization): Balance {
        val balance = Balance(0, BigDecimal.ZERO, organization)

        val savedBalance = balanceRepository.save(balance)

        balanceRegistryService.createBalanceRegistry(savedBalance)

        return savedBalance
    }

    @Transactional
    fun editBalance(balance: Balance, sum: BigDecimal): Balance {
        balance.allBalance = balance.allBalance.plus(sum)

        balanceRegistryService.createBalanceRegistry(balance)

        return balanceRepository.save(balance)
    }
}