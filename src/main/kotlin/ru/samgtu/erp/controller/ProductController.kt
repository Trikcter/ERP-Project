package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.ProductDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.ProductMapper
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.service.CrudService
import ru.samgtu.erp.service.ProductService

@RestController
@RequestMapping("/api/v1/product")
class ProductController : CrudRepository<ProductDTO, Product>() {
    @Autowired
    lateinit var productMapper: ProductMapper

    @Autowired
    lateinit var productService: ProductService

    override fun getMapper(): CrudMapper<ProductDTO, Product> {
        return productMapper
    }

    override fun getService(): CrudService<Product> {
        return productService
    }
}