package ru.samgtu.erp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samgtu.erp.dto.LoginDTO
import ru.samgtu.erp.dto.RegistrationDTO
import ru.samgtu.erp.service.AuthService
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController {
    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/sign-in")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginDTO): ResponseEntity<*> {
        return authService.authenticate(loginRequest)
    }

    @PostMapping("/sign-up")
    fun registerUser(@Valid @RequestBody newUser: RegistrationDTO): ResponseEntity<*> {
        return authService.registration(newUser)
    }
}