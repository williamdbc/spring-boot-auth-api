package br.com.application.validation;

import br.com.application.exception.BusinessRuleException;
import br.com.application.repository.UserRepository;
import br.com.application.utils.messages.UserMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkExistsById(Long id){
        if(!userRepository.existsById(id)){
            throw new BusinessRuleException(UserMessageUtil.USER_NOT_FOUND);
        }
    }

    public void checkEmailAlreadyInUse(String userEmail, Long id){
        if (userRepository.existsByEmailAndIdNot(userEmail, id)) {
            throw new BusinessRuleException(UserMessageUtil.EMAIL_ALREADY_IN_USE);
        }
    }


    public void checkPasswordMatches(String currentPassword, String userPassword){
        if(!passwordEncoder.matches(currentPassword, userPassword)){
            throw new BusinessRuleException(UserMessageUtil.CURRENT_PASSWORD_INCORRECT);
        }
    }

}
