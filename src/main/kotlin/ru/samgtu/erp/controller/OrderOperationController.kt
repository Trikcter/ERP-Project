package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.dto.OrderDTO
import ru.samgtu.erp.mapper.OrderOperationMapper
import ru.samgtu.erp.service.OrderOperationService

@RestController
@RequestMapping("/api/v1/orders")
class OrderOperationController {
    @Autowired
    private lateinit var orderOperationMapper: OrderOperationMapper

    @Autowired
    private lateinit var orderOperationService: OrderOperationService

    @PostMapping
    fun createOrder(@RequestParam organizationId: Long, @RequestParam warehouseId: Long, @RequestBody dto: OrderDTO) {
        orderOperationService.createOperation(organizationId, warehouseId, orderOperationMapper.dto2model(dto))
    }
}