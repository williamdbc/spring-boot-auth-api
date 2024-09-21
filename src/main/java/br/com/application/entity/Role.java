package br.com.application.entity;

import br.com.application.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private UserRoleEnum name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;
}
