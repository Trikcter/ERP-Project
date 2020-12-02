package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.BankOperation
import ru.samgtu.erp.repository.BankOperationRepository
import ru.samgtu.erp.service.validation.BalanceServiceValidation
import java.time.LocalDateTime

@Service
class BankOperationService {
    @Autowired
    private lateinit var bankOperationRepository: BankOperationRepository

    @Autowired
    private lateinit var typeOfFinancialOperationService: TypeOfFinancialOperationService

    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var balanceService: BalanceService

    @Autowired
    private lateinit var balanceServiceValidation: BalanceServiceValidation

    @Transactional
    fun create(organizationId: Long, operation: BankOperation) {
        val type = typeOfFinancialOperationService.getById(operation.typeOfFinancialOperation.id)
        val organization = organizationService.getById(organizationId)

        operation.dateOfOperation = LocalDateTime.now()

        balanceServiceValidation.validate(organization.balance, operation.sum)
        balanceService.editBalance(organization.balance, operation.sum)

        operation.balance = organization.balance

        operation.typeOfFinancialOperation = type

        bankOperationRepository.save(operation)
    }
}