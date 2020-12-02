package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.ProductDTO
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.Product

@Component
class ProductMapper : CrudMapper<ProductDTO, Product> {
    override fun dto2model(dto: ProductDTO): Product {
        return Product(
            dto.id,
            dto.title,
            dto.code,
            dto.price,
            Organization(dto.organizationId),
            dto.isDeleted
        )
    }

    override fun model2dto(model: Product): ProductDTO {
        return ProductDTO(
            model.id,
            model.title,
            model.code,
            model.price,
            model.organization.id,
            model.isDeleted
        )
    }
}