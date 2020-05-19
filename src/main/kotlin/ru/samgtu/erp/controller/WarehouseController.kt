package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/all/{id}")
    fun getAll(@PathVariable id: Long, pageable: Pageable): Page<WarehouseDTO> {
        return warehouseService.getAllById(id, pageable)
                .map { entity -> warehouseMapper.model2dto(entity) }
    }

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