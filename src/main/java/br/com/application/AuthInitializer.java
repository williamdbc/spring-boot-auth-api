package br.com.application;

import br.com.application.controller.AuthController;
import br.com.application.dto.RegisterDTO;
import br.com.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AuthInitializer implements CommandLineRunner {

    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createUsers();
    }

    public void createUsers(){
        RegisterDTO user1 = new RegisterDTO();
        user1.setName("admin");
        user1.setEmail("admin@admin.com");
        user1.setPassword("admin123");
        user1.setRoleId(1L);
        saveUser(user1);
    }

    public void saveUser(RegisterDTO registerDTO){
        if(userRepository.findByEmail(registerDTO.getEmail()) == null){
            authController.register(registerDTO);
        }
    }
}
