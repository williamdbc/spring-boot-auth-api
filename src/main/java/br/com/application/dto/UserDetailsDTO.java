package br.com.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDetailsDTO {

    private Long id;
    private String name;
    private String email;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

}
