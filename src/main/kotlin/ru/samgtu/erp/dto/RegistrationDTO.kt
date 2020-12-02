package ru.samgtu.erp.dto

import javax.validation.constraints.NotNull

data class RegistrationDTO(
    @NotNull
    var username: String,

    @NotNull
    var password: String,

    @NotNull
    var fio: String
)