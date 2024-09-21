package br.com.application.dto;

import lombok.Data;

@Data
public class UserEditInfoDTO {

    private String name;
    private String email;
    private Long roleId;

}
