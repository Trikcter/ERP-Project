package ru.samgtu.erp.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import ru.samgtu.erp.model.Balance
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.BalanceRepository
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
internal class BalanceServiceTest {

    @Mock
    lateinit var balanceRepository: BalanceRepository

    @Mock
    lateinit var balanceRegistryService: BalanceRegistryService

    @InjectMocks
    lateinit var balanceService: BalanceService

    @Test
    fun createBalance() {
        val organization = Organization(0, "", "", "", "", false)
        val defaultBalance = Balance(0, BigDecimal.ZERO, organization)

        Mockito
            .`when`(balanceRepository.save(Mockito.any<Balance>()))
            .thenReturn(defaultBalance)

        val balance = balanceService.createBalance(organization)
        assertTrue(balance.allBalance == BigDecimal.ZERO)
        assertTrue(balance.organization == organization)

        Mockito
            .verify(balanceRepository, Mockito.times(1))
            .save(Mockito.any<Balance>())
        Mockito
            .verify(balanceRegistryService, Mockito.times(1))
            .createBalanceRegistry(Mockito.any<Balance>())
    }

    @Test
    fun editBalance() {
    }
}