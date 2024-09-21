package br.com.application.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private String name;
    private String email;
    private String password;
    private Long roleId;

}
