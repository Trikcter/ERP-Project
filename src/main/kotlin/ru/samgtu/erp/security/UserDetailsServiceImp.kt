package ru.samgtu.erp.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.samgtu.erp.repository.UserRepository
import java.util.stream.Collectors

@Service
class UserDetailsServiceImp : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByLogin(username).get()

        val authorities: List<GrantedAuthority> = user.roles!!.stream().map { role -> SimpleGrantedAuthority(role.name) }.collect(Collectors.toList<GrantedAuthority>())

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.password)
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build()
    }
}