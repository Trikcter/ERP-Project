package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samgtu.erp.model.Order
import ru.samgtu.erp.repository.OrderRepository
import ru.samgtu.erp.service.validation.WarehouseValidService
import java.time.LocalDateTime

@Service
class OrderOperationService {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var warehouseService: WarehouseService

    @Autowired
    private lateinit var warehouseConditionService: WarehouseConditionService

    @Autowired
    private lateinit var typeOfWarehouseOperationService: TypeOfWarehouseOperationService

    @Autowired
    private lateinit var warehouseValidService: WarehouseValidService

    @Autowired
    private lateinit var balanceService: BalanceService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Transactional
    fun createOperation(organizationId: Long, warehouseId: Long, order: Order) {
        val organization = organizationService.getById(organizationId)
        val warehouse = warehouseService.getById(warehouseId)
        val product = productService.getById(order.product.id)

        val negativeCount = order.count * -1

        warehouseValidService.validate(product, warehouse, negativeCount)
        val type = typeOfWarehouseOperationService.getById(1)
        warehouseConditionService.createCondition(negativeCount, product, warehouse, type)

        order.product = product

        val balance = balanceService.editBalance(organization.balance, order.product.price)
        order.balance = balance

        order.dateOfOperation = LocalDateTime.now()

        orderRepository.save(order)
    }
}