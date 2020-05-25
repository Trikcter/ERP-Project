package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.TypeOfWarehouseDTO
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.TypeOfWarehouseOperation

@Component
class TypeOfWarehouseMapper : CrudMapper<TypeOfWarehouseDTO, TypeOfWarehouseOperation> {
    override fun dto2model(dto: TypeOfWarehouseDTO): TypeOfWarehouseOperation {
        return TypeOfWarehouseOperation(
                dto.id,
                dto.description,
                dto.name,
                dto.isDeleted,
                Organization(dto.organizationId)
        )
    }

    override fun model2dto(model: TypeOfWarehouseOperation): TypeOfWarehouseDTO {
        return TypeOfWarehouseDTO(
                model.id,
                model.description,
                model.name,
                model.organization.id,
                model.isDeleted
        )
    }
}