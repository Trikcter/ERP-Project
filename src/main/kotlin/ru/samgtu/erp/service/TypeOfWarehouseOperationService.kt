package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.TypeOfWarehouseOperation
import ru.samgtu.erp.repository.HandbookRepository
import ru.samgtu.erp.repository.TypeOfWarehouseOperationRepository

@Service
class TypeOfWarehouseOperationService : HandbookService<TypeOfWarehouseOperation>() {
    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var typeOfWarehouseRepository: TypeOfWarehouseOperationRepository

    override fun getOrganizationService(): CrudService<Organization> {
        return organizationService
    }

    override fun getRepository(): HandbookRepository<TypeOfWarehouseOperation> {
        return typeOfWarehouseRepository
    }
}