package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.WarehousesHistoryDTO
import ru.samgtu.erp.model.WarehouseOperation

@Component
class WarehouseOperationMapper {
    fun model2DTO(model: WarehouseOperation): WarehousesHistoryDTO {
        return WarehousesHistoryDTO(
                model.count,
                model.dateOfOperation,
                model.warehouse.title,
                model.product.title,
                model.typeOfWarehouseOperation.name
        )
    }
}