package ru.samgtu.erp.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import ru.samgtu.erp.service.RedisJWTService
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    private val tokenProvider: JwtProvider? = null

    @Autowired
    private val userDetailsService: UserDetailsServiceImp? = null

    @Autowired
    private val redisJWTService: RedisJWTService? = null

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = getJwt(request)
            if (jwt != null && tokenProvider!!.validateJwtToken(jwt)) {
                val username = tokenProvider.getUserNameFromJwtToken(jwt)

                val userDetails = userDetailsService!!.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Can NOT set user authentication -> Message: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.replace("Bearer ", "")

            return if (redisJWTService?.checkValue(token)!!) {
                token
            } else {
                null
            }

        } else null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }
}