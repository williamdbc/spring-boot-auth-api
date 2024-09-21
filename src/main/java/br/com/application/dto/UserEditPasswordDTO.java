package br.com.application.dto;

import lombok.Data;

@Data
public class UserEditPasswordDTO {

    private String currentPassword;
    private String newPassword;

}
