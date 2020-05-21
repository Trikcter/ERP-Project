package ru.samgtu.erp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.repository.WarehouseConditionRepository
import ru.samgtu.erp.repository.WarehouseOperationRepository

@Service
class WarehouseValidateService {
    @Autowired
    private lateinit var warehouseOperationRepository: WarehouseOperationRepository

    @Autowired
    private lateinit var warehouseConditionRepository: WarehouseConditionRepository

    fun validateOperationWithWarehouse() {

    }
}