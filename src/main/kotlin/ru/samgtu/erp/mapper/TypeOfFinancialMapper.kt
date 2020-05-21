package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.TypeOfFinancialDTO
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.TypeOfFinancialOperation

@Component
class TypeOfFinancialMapper : CrudMapper<TypeOfFinancialDTO, TypeOfFinancialOperation> {
    override fun dto2model(dto: TypeOfFinancialDTO): TypeOfFinancialOperation {
        return TypeOfFinancialOperation(
                dto.id,
                dto.description,
                dto.name,
                dto.isDeleted,
                Organization(dto.organizationId)
        )
    }

    override fun model2dto(model: TypeOfFinancialOperation): TypeOfFinancialDTO {
        return TypeOfFinancialDTO(
                model.id,
                model.description,
                model.name,
                model.organization.id,
                model.isDeleted
        )
    }
}