package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.repository.ProductRepository

@Service
class ProductService : CrudService<Product>() {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var organizationService: OrganizationService

    override fun getRepository(): JpaRepository<Product, Long> {
        return productRepository
    }

    override fun save(entity: Product): Product {
        val organization = organizationService.getById(entity.organization.id)
        entity.organization = organization

        return productRepository.save(entity)
    }
}