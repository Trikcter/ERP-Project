package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.TypeOfFinancialDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.TypeOfFinancialMapper
import ru.samgtu.erp.model.TypeOfFinancialOperation
import ru.samgtu.erp.service.HandbookService
import ru.samgtu.erp.service.TypeOfFinancialOperationService

@RestController
@RequestMapping("/api/v1/financial_operation")
class TypeOfFinancialController : HandbookController<TypeOfFinancialDTO, TypeOfFinancialOperation>() {
    @Autowired
    private lateinit var typeOfFinancialOperationService: TypeOfFinancialOperationService

    @Autowired
    private lateinit var typeOfFinancialMapper: TypeOfFinancialMapper

    override fun getService(): HandbookService<TypeOfFinancialOperation> {
        return typeOfFinancialOperationService
    }

    override fun getMapper(): CrudMapper<TypeOfFinancialDTO, TypeOfFinancialOperation> {
        return typeOfFinancialMapper
    }
}