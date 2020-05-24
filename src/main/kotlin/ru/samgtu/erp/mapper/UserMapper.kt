package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.RoleDTO
import ru.samgtu.erp.dto.UserDTO
import ru.samgtu.erp.model.Role
import ru.samgtu.erp.model.User
import ru.samgtu.erp.utils.StringUtils

@Component
class UserMapper : CrudMapper<UserDTO, User> {
    override fun dto2model(dto: UserDTO): User {
        val user = User(
                dto.id,
                dto.login,
                dto.password,
                StringUtils.getNameFromFio(dto.fio),
                StringUtils.getSecondNameFromFio(dto.fio),
                StringUtils.getSurnameFromFio(dto.fio)
        )

        user.roles = listOf(Role(
                0,
                dto.role.name,
                dto.role.description
        ))

        return user
    }

    override fun model2dto(model: User): UserDTO {
        val role = ArrayList(model.roles!!)[0]

        return UserDTO(
                model.id,
                model.login,
                model.password,
                String.format("%s %s %s",
                        model.firstName,
                        model.secondName,
                        model.surname),
                RoleDTO(
                        role.name,
                        role.description
                ),
                model.isDeleted
        )
    }
}