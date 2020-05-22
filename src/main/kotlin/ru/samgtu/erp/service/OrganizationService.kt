package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Organization
import ru.samgtu.erp.repository.AddressRepository
import ru.samgtu.erp.repository.OrganizationRepository
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletResponse

@Service
class OrganizationService : CrudService<Organization>() {
    @Autowired
    private lateinit var organizationRepository: OrganizationRepository

    @Autowired
    private lateinit var balanceService: BalanceService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var addressRepository: AddressRepository

    @Value("\${upload.path}")
    private lateinit var path: String

    @Transactional
    override fun save(entity: Organization): Organization {
        var address = addressRepository
                .findByTitle(entity.address.title)
                .orElseGet { Address(entity.address.title) }

        if (address.id == 0L) {
            address = addressRepository.save(entity.address)
        }

        entity.address = address

        val id = entity.id
        val saved = organizationRepository.save(entity)

        val user = userService.getCurrentUser()
        user.organization = saved
        userService.saveUser(user)

        return if (id == 0L) {
            val balance = balanceService.createBalance(saved)
            saved.balance = balance
            saved.owner = userService.getCurrentUser()

            organizationRepository.save(saved)
        } else {
            saved
        }
    }

    @Transactional
    fun saveFile(file: MultipartFile, id: Long): ResponseEntity<*> {
        val organization = this.getById(id)

        val dir = File(path)

        if (!dir.exists()) {
            dir.mkdir()
        }

        val uuidFile = UUID.randomUUID().toString()
        val filename = dir.absolutePath + "/" + uuidFile + "_" + file.originalFilename

        file.transferTo(File(filename))

        organization.url = filename
        organizationRepository.save(organization)

        return ResponseEntity.ok("Лого добавлено!")
    }

    fun getFileById(id: Long, response: HttpServletResponse) {
        val organization = organizationRepository
                .findById(id)
                .orElseThrow { throw EntityNotFoundException() }

        val filePath = Paths.get(organization.url)
        val bytes = Files.readAllBytes(filePath)

        response.outputStream.use {
            it.write(bytes)
            it.flush()
        }
    }

    override fun getRepository(): JpaRepository<Organization, Long> {
        return organizationRepository
    }
}