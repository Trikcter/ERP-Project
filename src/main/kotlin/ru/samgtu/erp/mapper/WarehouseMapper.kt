package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.WarehouseDTO
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.Warehouse

@Component
class WarehouseMapper : CrudMapper<WarehouseDTO, Warehouse> {
    override fun dto2model(dto: WarehouseDTO): Warehouse {
        val warehouse = Warehouse(
                dto.id,
                dto.title,
                dto.volume,
                dto.isDeleted
        )

        warehouse.address = Address(dto.addressTitle)
        warehouse.organization = Organization(dto.organizationId)

        return warehouse
    }

    override fun model2dto(model: Warehouse): WarehouseDTO {
        val dto = WarehouseDTO(
                model.id,
                model.title,
                model.organization.id,
                model.address.title,
                model.volume,
                model.isDeleted
        )

        dto.congestion = model.congestion

        return dto
    }
}