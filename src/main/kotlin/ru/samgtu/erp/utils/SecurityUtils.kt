package ru.samgtu.erp.utils

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class SecurityUtils {
    companion object {
        private fun getAuthentication(): Authentication {
            return SecurityContextHolder.getContext().authentication
        }

        private fun getPrincipal(): Any {
            return this.getAuthentication().principal
        }

        fun getUserName(): String {
            return this.getAuthentication().name
        }
    }
}