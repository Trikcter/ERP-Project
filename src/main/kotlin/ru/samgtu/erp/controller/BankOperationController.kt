package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.dto.BankOperationDTO
import ru.samgtu.erp.mapper.BankOperationMapper
import ru.samgtu.erp.service.BankOperationService

@RestController
@RequestMapping("/api/v1/bank_operations")
class BankOperationController {
    @Autowired
    private lateinit var bankOperationMapper: BankOperationMapper

    @Autowired
    private lateinit var bankOperationService: BankOperationService

    @PostMapping
    fun createOperation(@RequestParam organizationId: Long, @RequestBody operation: BankOperationDTO) {
        bankOperationService.create(organizationId, bankOperationMapper.dto2model(operation))
    }
}