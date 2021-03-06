package ru.samgtu.erp.service.validation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.exception.ERPException
import ru.samgtu.erp.model.Product
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.repository.WarehouseConditionRepository

/**
 * Сервис валидации операций, производимых со складом
 */
@Service
class WarehouseValidService {
    @Autowired
    private lateinit var warehouseConditionRepository: WarehouseConditionRepository

    /**
     * Валидация проведения операции на складе
     *
     * @param product - продукт
     * @param warehouse - склад
     * @param count - количество продукции
     */
    fun validate(product: Product, warehouse: Warehouse, count: Long) {
        val optionalCondition = warehouseConditionRepository.findFirstByProductAndWarehouse(product, warehouse)

        if (count < 0 && !optionalCondition.isPresent) {
            throw ERPException("На складе нет товаров данной категории!")
        }

        if (optionalCondition.isPresent) {
            val condition = optionalCondition.get()
            val futureCount = condition.count + count

            if (futureCount < 0) {
                throw ERPException("На складе не достаточно товаров данного типа")
            }

            if (count > 0) {
                if (futureCount > this.getFreeSpace(warehouse)) {
                    throw ERPException("На складе нет места")
                }
            }
        }

        if (count > 0 && !optionalCondition.isPresent) {
            if (count > this.getFreeSpace(warehouse)) {
                throw ERPException("На складе нет места")
            }
        }
    }

    private fun getFreeSpace(warehouse: Warehouse): Long {
        val count = warehouse.conditions?.map { it.count }?.sum()

        return warehouse.volume - count!!
    }
}