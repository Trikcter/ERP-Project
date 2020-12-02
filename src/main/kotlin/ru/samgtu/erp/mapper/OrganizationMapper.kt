package ru.samgtu.erp.mapper

import org.springframework.stereotype.Component
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.model.Address
import ru.samgtu.erp.model.Organization

@Component
class OrganizationMapper : CrudMapper<OrganizationDTO, Organization> {
    override fun dto2model(dto: OrganizationDTO): Organization {
        val organization = Organization(
            dto.id,
            dto.inn,
            dto.kpp,
            dto.ogrn,
            dto.title,
            dto.isDeleted
        )

        organization.address = Address(dto.addressTitle)

        return organization
    }

    override fun model2dto(model: Organization): OrganizationDTO {
        return OrganizationDTO(
            model.id,
            model.inn,
            model.kpp,
            model.ogrn,
            model.title,
            model.address.title,
            model.isDeleted
        )
    }
}