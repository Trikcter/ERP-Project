package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.dto.UserDTO
import ru.samgtu.erp.mapper.UserMapper
import ru.samgtu.erp.service.UserService

@RestController
@RequestMapping("/api/v1/organizations/workers")
class WorkerController {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userMapper: UserMapper

    @PostMapping
    fun createWorker(@RequestParam organizationId: Long, @RequestBody user: UserDTO) {
        userService.saveWorker(organizationId, userMapper.dto2model(user))
    }

    @PutMapping
    fun editWorker(@RequestParam organizationId: Long, @RequestBody user: UserDTO) {
        userService.saveWorker(organizationId, userMapper.dto2model(user))
    }

    @DeleteMapping
    fun deleteWorkers(@RequestBody ids: List<Long>) {
        userService.delete(ids)
    }

    @GetMapping
    fun getWorkersByOrganizationId(@RequestParam organizationId: Long, pageable: Pageable): Page<UserDTO> {
        return userService.getAllByOrganizationId(organizationId, pageable).map { userMapper.model2dto(it) }
    }
}