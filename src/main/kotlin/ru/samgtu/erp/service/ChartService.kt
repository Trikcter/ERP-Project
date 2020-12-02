package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.dto.ChartDTO
import ru.samgtu.erp.repository.BalanceRegistryRepository
import ru.samgtu.erp.repository.WarehouseRepository
import java.math.BigDecimal

@Service
class ChartService {
    @Autowired
    private lateinit var balanceRegistryRepository: BalanceRegistryRepository

    @Autowired
    private lateinit var warehouseRepository: WarehouseRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

    fun getBalanceChart(id: Long): ChartDTO<BigDecimal> {
        val organization = organizationService.getById(id)
        val registry = balanceRegistryRepository.findAllByBalance(organization.balance)

        val labels = registry.map { it.date.toString() }
        val data = registry.map { it.value }.toList()

        return ChartDTO(labels, data)
    }

    fun getWarehouseChart(id: Long): ChartDTO<Long?> {
        val organization = organizationService.getById(id)
        val registry = warehouseRepository.findAllByOrganizationAndIsDeletedIsFalse(organization)

        val labels = registry.map { it.title }
        val conditions: List<Long?> = registry
            .map { it.conditions?.map { condition -> condition.count }?.sum() }
            .toList()

        return ChartDTO(labels, conditions)
    }
}