package ru.samgtu.erp.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.samgtu.erp.model.Balance
import ru.samgtu.erp.repository.BalanceRegistryRepository
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
internal class BalanceRegistryServiceTest {
    @Mock
    lateinit var balanceRegistryRepository: BalanceRegistryRepository

    @InjectMocks
    lateinit var balanceRegistryService: BalanceRegistryService

    @Test
    fun createBalanceRegistry() {
        val balance = Balance(0, BigDecimal.TEN)
        balanceRegistryService.createBalanceRegistry(balance)

        Mockito
            .verify(balanceRegistryRepository, Mockito.times(1))
            .save(Mockito.any())
    }
}