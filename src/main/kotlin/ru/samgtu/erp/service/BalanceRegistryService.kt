package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.Balance
import ru.samgtu.erp.model.BalanceRegistry
import ru.samgtu.erp.repository.BalanceRegistryRepository
import java.time.LocalDateTime

@Service
class BalanceRegistryService {
    @Autowired
    private lateinit var balanceRegistryRepository: BalanceRegistryRepository

    @Transactional
    fun createBalanceRegistry(balance: Balance) {
        val balanceRegistry = BalanceRegistry(
                0,
                balance.allBalance,
                LocalDateTime.now(),
                balance
        )

        balanceRegistryRepository.save(balanceRegistry)
    }
}