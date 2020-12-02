package ru.samgtu.erp.service.validation

import org.springframework.stereotype.Service
import ru.samgtu.erp.exception.ERPException
import ru.samgtu.erp.model.Balance
import java.math.BigDecimal

@Service
class BalanceServiceValidation {

    fun validate(balance: Balance, sum: BigDecimal) {
        val futureBalance = balance.allBalance.plus(sum)

        if (futureBalance < BigDecimal.ZERO) {
            throw ERPException("Данная сумма превышает количество денег на балансе организации")
        }
    }
}