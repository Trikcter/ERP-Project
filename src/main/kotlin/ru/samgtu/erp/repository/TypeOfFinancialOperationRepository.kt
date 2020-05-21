package ru.samgtu.erp.repository

import org.springframework.stereotype.Repository
import ru.samgtu.erp.model.TypeOfFinancialOperation

@Repository
interface TypeOfFinancialOperationRepository : HandbookRepository<TypeOfFinancialOperation> {
}