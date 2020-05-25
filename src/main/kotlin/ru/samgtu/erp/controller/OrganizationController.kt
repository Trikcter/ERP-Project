package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.mapper.CrudMapper
import ru.samgtu.erp.mapper.OrganizationMapper
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.service.CrudService
import ru.samgtu.erp.service.OrganizationService
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/organizations")
class OrganizationController : CrudController<OrganizationDTO, Organization>() {
    @Autowired
    private lateinit var organizationService: OrganizationService

    @Autowired
    private lateinit var organizationMapper: OrganizationMapper

    @PostMapping("/file/{id}")
    fun saveFile(@RequestBody file: MultipartFile, @PathVariable id: Long): ResponseEntity<*> {
        return organizationService.saveFile(file, id)
    }

    @GetMapping("/file/{id}")
    fun getFile(@PathVariable id: Long, response: HttpServletResponse) {
        return organizationService.getFileById(id, response)
    }

    override fun getMapper(): CrudMapper<OrganizationDTO, Organization> {
        return organizationMapper
    }

    override fun getService(): CrudService<Organization> {
        return organizationService
    }
}