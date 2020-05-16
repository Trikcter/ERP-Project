package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.WarehouseDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.WarehouseMapper
import ru.samgtu.erp.model.Warehouse
import ru.samgtu.erp.service.CrudService
import ru.samgtu.erp.service.WarehouseService

@RestController
@RequestMapping("/api/v1/warehouse")
class WarehouseController : CrudController<WarehouseDTO, Warehouse>() {
    @Autowired
    private lateinit var warehouseMapper: WarehouseMapper

    @Autowired
    private lateinit var warehouseService: WarehouseService

    @PostMapping("/all")
    fun saveAll(@RequestBody warehouses: List<WarehouseDTO>): ResponseEntity<*> {
        val entities = warehouses.map {
            warehouseMapper.dto2model(it)
        }

        return warehouseService.saveAll(entities)
    }

    override fun getMapper(): CrudMapper<WarehouseDTO, Warehouse> {
        return warehouseMapper
    }

    override fun getService(): CrudService<Warehouse> {
        return warehouseService
    }
}