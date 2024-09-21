package br.com.application.service;

import br.com.application.dto.RoleDTO;
import br.com.application.entity.Role;
import br.com.application.enums.UserRoleEnum;
import br.com.application.mapper.RoleMapper;
import br.com.application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDTO> findAll(){
        List<Role> roleList = roleRepository.findAll();
        return roleMapper.toDto(roleList);
    }

    public Role assignRole(Long id){
        Role role = roleRepository.findById(id).orElse(null);
        return role != null ? role : roleRepository.findByName(UserRoleEnum.USER);
    }

    public UserRoleEnum getRoleEnumByName(String roleName){
        UserRoleEnum userRoleEnum = UserRoleEnum.fromRole(roleName);
        return userRoleEnum != null ? userRoleEnum : UserRoleEnum.USER;
    }

}
