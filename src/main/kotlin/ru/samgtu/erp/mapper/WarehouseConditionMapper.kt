package ru.samgtu.erp.mapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.WarehouseConditionDTO
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.model.WarehouseCondition

@Component
class WarehouseConditionMapper {
    @Autowired
    private lateinit var productMapper: ProductMapper

    fun dto2model(dto: WarehouseConditionDTO): WarehouseCondition {
        val model = WarehouseCondition(
                dto.id,
                dto.count
        )

        model.product = Product(dto.product.id)
        model.warehouse = Warehouse(dto.warehouseId)

        return model
    }

    fun model2dto(model: WarehouseCondition): WarehouseConditionDTO {
        return WarehouseConditionDTO(
                model.id,
                model.count,
                productMapper.model2dto(model.product),
                model.warehouse.id
        )
    }
}