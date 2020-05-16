package ru.samgtu.erp.dto

import javax.validation.constraints.NotNull

data class OrganizationDTO(
        var id: Long,

        @NotNull
        var inn: String,

        @NotNull
        var kpp: String,

        @NotNull
        var ogrn: String,

        @NotNull
        var title: String,

        var isDeleted: Boolean = false
)