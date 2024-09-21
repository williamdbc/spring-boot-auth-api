package br.com.application.mapper;

import br.com.application.dto.UserDetailsDTO;
import br.com.application.dto.UserEditInfoDTO;
import br.com.application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends EntityMapper<UserDetailsDTO, User>{

    @Mapping(source = "role.name", target = "roleName", qualifiedByName = "enumGetRole")
    UserDetailsDTO toDto(User entity);

}
