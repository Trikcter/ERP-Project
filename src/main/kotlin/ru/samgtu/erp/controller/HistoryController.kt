package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.BankHistory
import ru.samgtu.erp.dto.OrderHistory
import ru.samgtu.erp.dto.WarehousesHistoryDTO
import ru.samgtu.erp.mapper.BankOperationMapper
import ru.samgtu.erp.mapper.OrderOperationMapper
import ru.samgtu.erp.mapper.WarehouseOperationMapper
import ru.samgtu.erp.service.HistoryService

@RestController
@RequestMapping("/api/v1/history")
class HistoryController {
    @Autowired
    private lateinit var historyService: HistoryService

    @Autowired
    private lateinit var warehouseOperationMapper: WarehouseOperationMapper

    @Autowired
    private lateinit var orderOperationMapper: OrderOperationMapper

    @Autowired
    private lateinit var bankOperationMapper: BankOperationMapper

    @GetMapping("/warehouses")
    fun getWarehousesHistory(@RequestParam organizationId: Long,
                             @RequestParam warehouseId: Long?,
                             pageable: Pageable): Page<WarehousesHistoryDTO> {
        return historyService.getAllWarehouseHistory(organizationId, warehouseId, pageable)
                .map { warehouseOperationMapper.model2DTO(it) }
    }

    @GetMapping("/banks")
    fun getBankHistory(@RequestParam organizationId: Long,
                       pageable: Pageable): Page<BankHistory> {
        return historyService.getAllBankHistory(organizationId, pageable).map { bankOperationMapper.model2DTO(it) }
    }

    @GetMapping("/orders")
    fun getOrderHistory(@RequestParam organizationId: Long,
                        pageable: Pageable): Page<OrderHistory> {
        return historyService.getAllOrderHistory(organizationId, pageable).map { orderOperationMapper.model2DTO(it) }
    }
}