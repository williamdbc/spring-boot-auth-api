package br.com.application.service;

import br.com.application.dto.AuthDTO;
import br.com.application.dto.LoginDTO;
import br.com.application.dto.RegisterDTO;
import br.com.application.entity.Role;
import br.com.application.entity.User;
import br.com.application.infra.security.TokenService;
import br.com.application.repository.UserRepository;
import br.com.application.validation.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthValidator authValidator;

    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthDTO login(LoginDTO data) {
        User user = userRepository.findByEmail(data.getEmail());
        authValidator.checkEmailExists(user);
        authValidator.checkPasswordMatches(data.getPassword(), user.getPassword());

        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
        String token = tokenService.generateToken(user);

        return createAuth(user, token);
    }

    public void register(RegisterDTO data) {
        authValidator.checkEmailAlreadyInUse(data.getEmail());
        authValidator.checkPasswordIsEmpty(data.getPassword());

        User user = createUser(data);
        userRepository.save(user);
    }

    public void validateToken(String token){
        tokenService.validateToken(token);
    }

    private AuthDTO createAuth(User user, String token){
        AuthDTO auth = new AuthDTO();
        auth.setEmail(user.getEmail());
        auth.setToken(token);
        return auth;
    }

    private User createUser(RegisterDTO data){
        User user = new User();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        Role userRole = roleService.assignRole(data.getRoleId());
        user.setRole(userRole);
        return user;
    }

}