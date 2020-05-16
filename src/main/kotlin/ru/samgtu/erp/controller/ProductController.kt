package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
class ProductController : CrudController<ProductDTO, Product>() {
    @Autowired
    private lateinit var productMapper: ProductMapper

    @Autowired
    private lateinit var productService: ProductService

    @PostMapping("/all")
    fun saveAll(@RequestBody products: List<ProductDTO>): ResponseEntity<*> {
        val entities = products.map {
            productMapper.dto2model(it)
        }

        return productService.saveAll(entities)
    }

    override fun getMapper(): CrudMapper<ProductDTO, Product> {
        return productMapper
    }

    override fun getService(): CrudService<Product> {
        return productService
    }
}