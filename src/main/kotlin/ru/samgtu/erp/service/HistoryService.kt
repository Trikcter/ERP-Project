package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import ru.samgtu.erp.model.BankOperation
import ru.samgtu.erp.model.Order
import ru.samgtu.erp.model.WarehouseOperation
import ru.samgtu.erp.repository.BankOperationRepository
import ru.samgtu.erp.repository.OrderRepository
import ru.samgtu.erp.repository.WarehouseOperationRepository

@Service
class HistoryService {
    @Autowired
    private lateinit var warehouseOperationRepository: WarehouseOperationRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var bankOperationRepository: BankOperationRepository

    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var warehouseService: WarehouseService

    fun getAllWarehouseHistory(organizationId: Long, warehouseId: Long?, pageable: Pageable): Page<WarehouseOperation> {
        val organization = organizationService.getById(organizationId)

        return if (warehouseId != null) {
            val warehouse = warehouseService.getById(warehouseId)

            warehouseOperationRepository.findAllByOrganizationAndWarehouse(organization.id, warehouse.id, pageable)
        } else {
            warehouseOperationRepository.findAllByOrganization(organization.id, pageable)
        }
    }

    fun getAllOrderHistory(
        @RequestParam organizationId: Long,
        pageable: Pageable
    ): Page<Order> {
        organizationService.getById(organizationId)

        return orderRepository.findAllByOrganizationId(organizationId, pageable)
    }

    fun getAllBankHistory(
        @RequestParam organizationId: Long,
        pageable: Pageable
    ): Page<BankOperation> {
        organizationService.getById(organizationId)

        return bankOperationRepository.findAllByOrganizationId(organizationId, pageable)
    }
}