package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.samgtu.erp.dto.LoginDTO
import ru.samgtu.erp.dto.RegistrationDTO
import ru.samgtu.erp.model.User
import ru.samgtu.erp.repository.RoleRepository
import ru.samgtu.erp.repository.UserRepository
import ru.samgtu.erp.security.JwtProvider
import ru.samgtu.erp.security.JwtResponse
import ru.samgtu.erp.utils.StringUtils
import java.util.*
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class AuthService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var jwtProvider: JwtProvider

    fun authenticate(loginRequest: LoginDTO): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByLogin(loginRequest.username)

        return if (userCandidate.isPresent) {
            val user: User = userCandidate.get()

            val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
            SecurityContextHolder.getContext().authentication = authentication

            val jwt: String = jwtProvider.generateJwtToken(user.login)
            val authorities: List<GrantedAuthority> = user.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }
                    .collect(Collectors.toList<GrantedAuthority>())

            ResponseEntity.ok(JwtResponse(jwt, user.login, user.organization?.title, authorities))
        } else {
            ResponseEntity("Такого пользователя нет",
                    HttpStatus.BAD_REQUEST)
        }
    }

    @Transactional
    fun registration(newUser: RegistrationDTO): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByLogin(newUser.username)

        if (!userCandidate.isPresent) {
            if (userRepository.existsByLogin(newUser.username)) {
                return ResponseEntity("Это имя уже занято",
                        HttpStatus.BAD_REQUEST)
            }

            val user = User(
                    0,
                    newUser.username,
                    encoder.encode(newUser.password),
                    StringUtils.getNameFromFio(newUser.fio),
                    StringUtils.getSecondNameFromFio(newUser.fio),
                    StringUtils.getSurnameFromFio(newUser.fio),
                    false
            )
            user.roles = listOf(roleRepository.findByName("ADMIN"))

            val savedUser = userRepository.save(user)

            val jwt: String = jwtProvider.generateJwtToken(savedUser.login)
            val authorities: List<GrantedAuthority> = savedUser.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }
                    .collect(Collectors.toList<GrantedAuthority>())

            return ResponseEntity.ok(JwtResponse(jwt, user.login, user.organization?.title, authorities))
        } else {
            return ResponseEntity("Такой пользовать уже существует",
                    HttpStatus.BAD_REQUEST)
        }
    }
}