package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.RoleDTO
import ru.samgtu.erp.model.Role

@Component
class RoleMapper {
    fun model2DTO(model: Role): RoleDTO {
        return RoleDTO(
                model.name,
                model.description
        )
    }
}