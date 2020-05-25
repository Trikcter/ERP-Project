package ru.samgtu.erp.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.session.SessionManagementFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import ru.samgtu.erp.security.JwtAuthenticationEntryPoint
import ru.samgtu.erp.security.JwtAuthenticationFilter
import ru.samgtu.erp.security.UserDetailsServiceImp

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    internal var userDetailsService: UserDetailsServiceImp? = null

    @Autowired
    private val unauthorizedHandler: JwtAuthenticationEntryPoint? = null

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationJwtTokenFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter()
    }

    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/api/**", this.getCorsConfiguration())

        return CorsFilter(source)
    }

    private fun getCorsConfiguration(): CorsConfiguration {
        val configuration = CorsConfiguration()

        configuration.addAllowedOrigin("*")

        configuration.addAllowedMethod("PUT")
        configuration.addAllowedMethod("POST")
        configuration.addAllowedMethod("GET")
        configuration.addAllowedMethod("OPTIONS")
        configuration.addAllowedMethod("DELETE")
        configuration.addAllowedMethod("PATCH")

        configuration.addAllowedHeader("Origin")
        configuration.addAllowedHeader("X-Requested-With")
        configuration.addAllowedHeader("Content-Type")
        configuration.addAllowedHeader("Accept")
        configuration.addAllowedHeader("Content-Disposition")
        configuration.addAllowedHeader("Authorization")

        configuration.addExposedHeader("Content-Disposition")

        configuration.allowCredentials = true

        configuration.maxAge = 3600L

        return configuration
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/*", "/api/v1/organizations/file/*").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterBefore(corsFilter(), SessionManagementFilter::class.java)
    }
}