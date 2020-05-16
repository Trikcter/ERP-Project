package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.repository.ProductRepository

@Service
class ProductService : CrudService<Product>() {
    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

    override fun getRepository(): JpaRepository<Product, Long> {
        return productRepository
    }

    fun saveAll(products: List<Product>): ResponseEntity<*> {
        products.forEach {
            productRepository.save(it)
        }

        return ResponseEntity.ok("Вся продукция добавлена!")
    }
}