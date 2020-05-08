package ru.samgtu.erp.mapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.dto.ProductDTO
import ru.samgtu.erp.model.Product

@Service
class ProductMapper : CrudMapper<ProductDTO, Product> {
    @Autowired
    lateinit var organizationMapper: OrganizationMapper

    override fun dto2model(dto: ProductDTO): Product {
        return Product(
                dto.id,
                dto.title,
                dto.code,
                dto.price,
                organizationMapper.dto2model(dto.organizationDTO),
                dto.isDeleted
        )
    }

    override fun model2dto(model: Product): ProductDTO {
        return ProductDTO(
                model.id,
                model.title,
                model.code,
                model.price,
                organizationMapper.model2dto(model.organization),
                model.isDeleted
        )
    }
}