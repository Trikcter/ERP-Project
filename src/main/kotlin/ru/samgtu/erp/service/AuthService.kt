package ru.samgtu.erp.service

import org.springframework.beans.factory.annotation.Autowired
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
import ru.samgtu.erp.exception.ERPException
import ru.samgtu.erp.model.Role
import ru.samgtu.erp.model.User
import ru.samgtu.erp.repository.RoleRepository
import ru.samgtu.erp.repository.UserRepository
import ru.samgtu.erp.security.JwtProvider
import ru.samgtu.erp.security.JwtResponse
import ru.samgtu.erp.utils.StringUtils
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

/**
 * Сервис для аутентифкации пользователей
 */
@Service
class AuthService {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    @Autowired
    private lateinit var jwtProvider: JwtProvider

    @Autowired
    lateinit var redisJWTService: RedisJWTService

    /**
     * Аутентификация
     *
     * @param loginRequest - запрос на аутентификации (credentials)
     */
    fun authenticate(loginRequest: LoginDTO): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByLogin(loginRequest.username)

        return if (userCandidate.isPresent) {
            val user: User = userCandidate.get()

            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
            )
            SecurityContextHolder.getContext().authentication = authentication

            val jwt: String = jwtProvider.generateJwtToken(user.login)
            val authorities: List<GrantedAuthority> =
                user.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }
                    .collect(Collectors.toList<GrantedAuthority>())

            redisJWTService.createValue(jwt, user.login)

            ResponseEntity.ok(
                JwtResponse(
                    jwt,
                    user.login,
                    user.organization?.id,
                    user.organization?.title,
                    authorities,
                    StringUtils.getShortFio(user.firstName, user.surname, user.secondName)
                )
            )
        } else {
            throw ERPException("Такого пользователя нет!")
        }
    }

    /**
     * Регистрация нового пользователя через окно регистрации
     *
     * @param newUser - новый пользователь
     */
    @Transactional
    fun registration(newUser: RegistrationDTO): ResponseEntity<*> {
        val userCandidate: Optional<User> = userRepository.findByLogin(newUser.username)

        if (!userCandidate.isPresent) {
            if (userRepository.existsByLogin(newUser.username)) {
                throw ERPException("Это имя уже занято!")
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
            user.roles = listOf(roleRepository.findByName("CEO"))

            val savedUser = userRepository.save(user)

            val jwt: String = jwtProvider.generateJwtToken(savedUser.login)
            val authorities: List<GrantedAuthority> =
                savedUser.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }
                    .collect(Collectors.toList<GrantedAuthority>())

            redisJWTService.createValue(jwt, user.login)

            return ResponseEntity.ok(
                JwtResponse(
                    jwt,
                    user.login,
                    user.organization?.id,
                    user.organization?.title,
                    authorities,
                    StringUtils.getShortFio(user.firstName, user.surname, user.secondName)
                )
            )
        } else {
            throw ERPException("Такой пользователь уже существует!")
        }
    }

    fun logout(request: HttpServletRequest) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.replace("Bearer ", "")

            redisJWTService.deleteValue(token)
        }
    }

    fun getAllRoles(): List<Role> {
        return roleRepository.findAll()
            .filter { it.id != 1L }
            .toList()
    }
}