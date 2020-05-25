package ru.samgtu.erp.security

import org.springframework.security.core.GrantedAuthority

class JwtResponse(var accessToken: String?,
                  var username: String?,
                  val organizationId: Long?,
                  val organizationName: String?,
                  val authorities: Collection<GrantedAuthority>,
                  val fio: String?) {
    var type = "Bearer"
}