package br.com.application.service;

import br.com.application.dto.UserDetailsDTO;
import br.com.application.dto.UserEditInfoDTO;
import br.com.application.dto.UserEditPasswordDTO;
import br.com.application.entity.User;
import br.com.application.mapper.UserMapper;
import br.com.application.repository.UserRepository;
import br.com.application.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public void update(UserEditInfoDTO userEditInfoDTO, Long id){
        User user = findEntityById(id);
        userValidator.checkEmailAlreadyInUse(userEditInfoDTO.getEmail(), id);

        user.setName(userEditInfoDTO.getName());
        user.setEmail(userEditInfoDTO.getEmail());
        user.setRole(roleService.assignRole(userEditInfoDTO.getRoleId()));

        userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    public User findEntityById(Long id){
        userValidator.checkExistsById(id);
        return userRepository.findById(id).get();
    }

    public UserDetailsDTO findDtoById(Long id){
        User user = findEntityById(id);
        return userMapper.toDto(user);
    }

    public List<UserDetailsDTO> findAll() {
        List<User> userList = userRepository.findAll();
        return userMapper.toDto(userList);
    }

    public void changePassword(UserEditPasswordDTO userEditPasswordDTO, Long id){
        User user = findEntityById(id);
        userValidator.checkPasswordMatches(userEditPasswordDTO.getCurrentPassword(), user.getPassword());

        user.setPassword(passwordEncoder.encode(userEditPasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public String resetPasswordByAdmin(Long id){
        User user = findEntityById(id);

        String randomPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(randomPassword));
        userRepository.save(user);

        return randomPassword;
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

}
