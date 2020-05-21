package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.TypeOfWarehouseDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.TypeOfWarehouseMapper
import ru.samgtu.erp.model.TypeOfWarehouseOperation
import ru.samgtu.erp.service.HandbookService
import ru.samgtu.erp.service.TypeOfWarehouseOperationService

@RestController
@RequestMapping("/api/v1/warehouse_operation")
class TypeOfWarehouseController : HandbookController<TypeOfWarehouseDTO, TypeOfWarehouseOperation>() {
    @Autowired
    private lateinit var typeOfWarehouseService: TypeOfWarehouseOperationService

    @Autowired
    private lateinit var typeOfWarehouseMapper: TypeOfWarehouseMapper

    override fun getMapper(): CrudMapper<TypeOfWarehouseDTO, TypeOfWarehouseOperation> {
        return typeOfWarehouseMapper
    }

    override fun getService(): HandbookService<TypeOfWarehouseOperation> {
        return typeOfWarehouseService
    }
}