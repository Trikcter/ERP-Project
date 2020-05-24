package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.ChartDTO
import ru.samgtu.erp.service.ChartService
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1/chart")
class ChartController {
    @Autowired
    private lateinit var chartService: ChartService

    @GetMapping("/balance")
    fun getBalanceChart(@RequestParam organizationId: Long): ChartDTO<BigDecimal> {
        return chartService.getBalanceChart(organizationId)
    }

    @GetMapping("/warehouse")
    fun getWarehouseChart(@RequestParam organizationId: Long): ChartDTO<Long?> {
        return chartService.getWarehouseChart(organizationId)
    }
}