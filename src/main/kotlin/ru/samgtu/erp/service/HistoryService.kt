package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.WarehouseOperation
import ru.samgtu.erp.repository.WarehouseOperationRepository

@Service
class HistoryService {
    @Autowired
    private lateinit var warehouseOperationRepository: WarehouseOperationRepository

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
}