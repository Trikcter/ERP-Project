package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.WarehousesHistoryDTO
import ru.samgtu.erp.mapper.WarehouseOperationMapper
import ru.samgtu.erp.service.HistoryService

@RestController
@RequestMapping("/api/v1/history")
class HistoryController {
    @Autowired
    private lateinit var historyService: HistoryService

    @Autowired
    private lateinit var warehouseOperationMapper: WarehouseOperationMapper

    @GetMapping("/warehouses")
    fun getWarehousesHistory(@RequestParam organizationId: Long,
                             @RequestParam warehouseId: Long?,
                             pageable: Pageable): Page<WarehousesHistoryDTO> {
        return historyService.getAllWarehouseHistory(organizationId, warehouseId, pageable)
                .map { warehouseOperationMapper.model2DTO(it) }
    }

    @GetMapping("/financial")
    fun getFinancialHistory() {

    }
}