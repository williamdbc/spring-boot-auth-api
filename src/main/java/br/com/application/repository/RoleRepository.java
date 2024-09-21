package br.com.application.repository;

import br.com.application.entity.Role;
import br.com.application.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRoleEnum name);
}
