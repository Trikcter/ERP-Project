package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.OrganizationMapper
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.service.CrudService
import ru.samgtu.erp.service.OrganizationService

@RestController
@RequestMapping("/api/v1/organization")
class OrganizationController : CrudRepository<OrganizationDTO, Organization>() {
    @Autowired
    lateinit var organizationService: OrganizationService

    @Autowired
    lateinit var organizationMapper: OrganizationMapper

    override fun getMapper(): CrudMapper<OrganizationDTO, Organization> {
        return organizationMapper
    }

    override fun getService(): CrudService<Organization> {
        return organizationService
    }
}