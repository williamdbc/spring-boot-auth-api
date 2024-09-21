package br.com.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    ADMIN("Administrador"),
    PROJECT_MANAGER("Gerente de Projeto"),
    DEVELOPER("Desenvolvedor"),
    TESTER("Tester"),
    ANALYST("Analista"),
    USER("Usuário Comum");

    private final String role;

    public static UserRoleEnum fromRole(String role) {
        return Arrays.stream(values())
                .filter(userRole -> userRole.getRole().equals(role))
                .findFirst()
                .orElseThrow(null);
    }
}