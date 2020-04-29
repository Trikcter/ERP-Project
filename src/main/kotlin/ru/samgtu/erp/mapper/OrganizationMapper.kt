package ru.samgtu.erp.mapper

import org.springframework.stereotype.Service
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.model.Organization

@Service
class OrganizationMapper {
    fun dto2model(dto: OrganizationDTO): Organization {
        return Organization(
                dto.id,
                dto.inn,
                dto.kpp,
                dto.ogrn,
                dto.title,
                dto.isDeleted
        )
    }

    fun model2dto(organization: Organization): OrganizationDTO {
        return OrganizationDTO(
                organization.id,
                organization.inn,
                organization.kpp,
                organization.ogrn,
                organization.title,
                organization.isDeleted
        )
    }
}