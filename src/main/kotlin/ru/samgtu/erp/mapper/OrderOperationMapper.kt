package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.OrderDTO
import ru.samgtu.erp.dto.OrderHistory
import ru.samgtu.erp.model.Order
import ru.samgtu.erp.model.Product
import java.time.LocalDateTime

@Component
class OrderOperationMapper {
    fun model2DTO(model: Order): OrderHistory {
        return OrderHistory(
                model.count,
                model.product.title,
                model.dateOfOperation
        )
    }

    fun dto2model(dto: OrderDTO): Order {
        val order = Order(
                0,
                dto.count,
                LocalDateTime.now()
        )

        order.product = Product(dto.productId)

        return order
    }
}