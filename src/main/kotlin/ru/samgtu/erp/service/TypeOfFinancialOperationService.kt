package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.model.TypeOfFinancialOperation
import ru.samgtu.erp.repository.HandbookRepository
import ru.samgtu.erp.repository.TypeOfFinancialOperationRepository

@Service
class TypeOfFinancialOperationService : HandbookService<TypeOfFinancialOperation>() {
    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var typeOfFinancialOperationRepository: TypeOfFinancialOperationRepository

    override fun getOrganizationService(): CrudService<Organization> {
        return organizationService
    }

    override fun getRepository(): HandbookRepository<TypeOfFinancialOperation> {
        return typeOfFinancialOperationRepository
    }
}