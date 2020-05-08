package ru.samgtu.erp.mapper

import org.springframework.stereotype.Service
import ru.samgtu.erp.dto.OrganizationDTO
import ru.samgtu.erp.model.Organization

@Service
class OrganizationMapper : CrudMapper<OrganizationDTO, Organization> {
    override fun dto2model(dto: OrganizationDTO): Organization {
        return Organization(
                dto.id,
                dto.inn,
                dto.kpp,
                dto.ogrn,
                dto.title,
                dto.isDeleted
        )
    }

    override fun model2dto(model: Organization): OrganizationDTO {
        return OrganizationDTO(
                model.id,
                model.inn,
                model.kpp,
                model.ogrn,
                model.title,
                model.isDeleted
        )
    }
}