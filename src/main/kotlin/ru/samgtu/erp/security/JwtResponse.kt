package ru.samgtu.erp.security

import org.springframework.security.core.GrantedAuthority

class JwtResponse(var accessToken: String?, var username: String?, val authorities: Collection<GrantedAuthority>) {
    var type = "Bearer"
}