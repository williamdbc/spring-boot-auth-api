package br.com.application.mapper;

import br.com.application.dto.UserDetailsDTO;
import br.com.application.entity.Role;
import br.com.application.entity.User;
import br.com.application.enums.UserRoleEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T23:03:31-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toEntity(UserDetailsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setCreatedAt( dto.getCreatedAt() );
        user.setLastLoginAt( dto.getLastLoginAt() );

        return user;
    }

    @Override
    public List<User> toEntity(List<UserDetailsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDetailsDTO userDetailsDTO : dtoList ) {
            list.add( toEntity( userDetailsDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDetailsDTO> toDto(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDetailsDTO> list = new ArrayList<UserDetailsDTO>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public UserDetailsDTO toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        userDetailsDTO.setRoleName( roleMapper.enumGetRole( entityRoleName( entity ) ) );
        userDetailsDTO.setId( entity.getId() );
        userDetailsDTO.setName( entity.getName() );
        userDetailsDTO.setEmail( entity.getEmail() );
        userDetailsDTO.setCreatedAt( entity.getCreatedAt() );
        userDetailsDTO.setLastLoginAt( entity.getLastLoginAt() );

        return userDetailsDTO;
    }

    private UserRoleEnum entityRoleName(User user) {
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        return role.getName();
    }
}
