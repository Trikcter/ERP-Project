package ru.samgtu.erp.service.validation

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import ru.samgtu.erp.exception.ERPException
import ru.samgtu.erp.model.Balance
import java.math.BigDecimal

@ExtendWith(MockitoExtension::class)
internal class BalanceServiceValidationTest {
    @Spy
    lateinit var balanceServiceValidation: BalanceServiceValidation

    private val defaultBalance = Balance(0, BigDecimal.TEN)

    @Test
    fun positiveValidate() {
        assertDoesNotThrow { balanceServiceValidation.validate(defaultBalance, BigDecimal.TEN) }
    }

    @Test
    fun negativeValidate() {
        assertThrows<ERPException> { balanceServiceValidation.validate(defaultBalance, BigDecimal.valueOf(-20)) }
    }
}