package ru.samgtu.erp.dto

import javax.validation.constraints.NotNull

data class LoginDTO(
        @NotNull
        var username: String,

        @NotNull
        var password: String
)