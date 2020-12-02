package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.BankHistory
import ru.samgtu.erp.dto.BankOperationDTO
import ru.samgtu.erp.model.BankOperation
import ru.samgtu.erp.model.TypeOfFinancialOperation
import java.time.LocalDateTime

@Component
class BankOperationMapper {
    fun model2DTO(model: BankOperation): BankHistory {
        return BankHistory(
            model.sum,
            model.comment,
            model.dateOfOperation,
            model.typeOfFinancialOperation.name
        )
    }

    fun dto2model(dto: BankOperationDTO): BankOperation {
        val operation = BankOperation(
            0,
            dto.sum,
            dto.comment,
            LocalDateTime.now()
        )

        operation.typeOfFinancialOperation = TypeOfFinancialOperation(dto.typeId)

        return operation
    }
}