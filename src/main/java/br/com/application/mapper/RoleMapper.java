package br.com.application.mapper;

import br.com.application.dto.RoleDTO;
import br.com.application.entity.Role;
import br.com.application.enums.UserRoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    @Mapping(source = "name", target = "name", qualifiedByName = "enumGetRole")
    RoleDTO toDto(Role entity);

    @Named("enumGetRole")
    default String enumGetRole(UserRoleEnum roleEnum) {
        return roleEnum != null ? roleEnum.getRole() : null;
    }

}
