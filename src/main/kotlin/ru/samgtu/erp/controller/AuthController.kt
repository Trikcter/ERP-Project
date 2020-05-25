package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.samgtu.erp.dto.LoginDTO
import ru.samgtu.erp.dto.RegistrationDTO
import ru.samgtu.erp.dto.RoleDTO
import ru.samgtu.erp.mapper.RoleMapper
import ru.samgtu.erp.service.AuthService
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var roleMapper: RoleMapper

    @PostMapping("/sign-in")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginDTO): ResponseEntity<*> {
        return authService.authenticate(loginRequest)
    }

    @PostMapping("/sign-up")
    fun registerUser(@Valid @RequestBody newUser: RegistrationDTO): ResponseEntity<*> {
        return authService.registration(newUser)
    }

    @GetMapping("/logout")
    fun logoutUser(request: HttpServletRequest) {
        authService.logout(request)
    }

    @GetMapping("/roles")
    fun getRoles(): List<RoleDTO> {
        return authService.getAllRoles().map { roleMapper.model2DTO(it) }
    }
}