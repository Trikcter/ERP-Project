package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.*
import ru.samgtu.erp.repository.WarehouseConditionRepository
import ru.samgtu.erp.repository.WarehouseOperationRepository
import java.time.LocalDateTime

/**
 * Сервис для оперирования операциям со складом
 */
@Service
class WarehouseConditionService {
    @Autowired
    private lateinit var warehouseOperationRepository: WarehouseOperationRepository

    @Autowired
    private lateinit var warehouseConditionRepository: WarehouseConditionRepository

    /**
     * Создание нового состояния склада и занесение в реестр
     */
    fun createCondition(count: Long, product: Product, warehouse: Warehouse, typeOperation: TypeOfWarehouseOperation) {
        val condition = warehouseConditionRepository.findFirstByProductAndWarehouse(product, warehouse)
            .orElse(WarehouseCondition(0, 0))

        condition.count += count

        if (condition.id == 0L) {
            condition.product = product
            condition.warehouse = warehouse
        }

        if (condition.count == 0L) {
            warehouseConditionRepository.delete(condition)
        } else {
            warehouseConditionRepository.save(condition)
        }

        val operation = WarehouseOperation(
            0,
            count,
            LocalDateTime.now(),
            typeOperation,
            warehouse,
            product
        )

        warehouseOperationRepository.save(operation)
    }
}