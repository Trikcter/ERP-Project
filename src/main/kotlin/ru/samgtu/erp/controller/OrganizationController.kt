package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.mapper.OrganizationMapper
import ru.samgtu.erp.service.OrganizationService

@RestController
@RequestMapping("/api/v1/organization")
class OrganizationController {
    @Autowired
    lateinit var organizationService: OrganizationService

    @Autowired
    lateinit var organizationMapper: OrganizationMapper

    @GetMapping("{id}")
    fun getOrganizationById(@PathVariable id: Long): OrganizationDTO {
        return organizationMapper.model2dto(organizationService.getOrganization(id))
    }

    @GetMapping("/all")
    fun getAllOrganizations(page: Pageable): Page<OrganizationDTO> {
        return organizationService.getAll(page)
                .map { organization -> organizationMapper.model2dto(organization) }
    }

    @PostMapping
    fun addOrganization(@RequestBody organizationDto: OrganizationDTO): OrganizationDTO {
        return organizationMapper.model2dto(
                organizationService.save(organizationMapper.dto2model(organizationDto))
        )
    }

    @PutMapping
    fun editOrganization(@RequestBody organizationDto: OrganizationDTO): OrganizationDTO {
        return organizationMapper.model2dto(
                organizationService.save(organizationMapper.dto2model(organizationDto))
        )
    }

    @DeleteMapping
    fun deleteOrganizationsById(@RequestBody ids: List<Long>): ResponseEntity<Any> {
        organizationService.delete(ids)
        return ResponseEntity.ok().build()
    }
}