package br.com.application.validation;

import br.com.application.dto.LoginDTO;
import br.com.application.entity.User;
import br.com.application.exception.BusinessRuleException;
import br.com.application.repository.UserRepository;
import br.com.application.utils.messages.AuthMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void checkEmailExists(User user){
        if(user == null) {
            throw new BusinessRuleException(AuthMessageUtil.EMAIL_OR_PASSWORD_INCORRECT);
        }
    }

    public void checkPasswordMatches(String loginPassword, String userPassword){
        if(!passwordEncoder.matches(loginPassword, userPassword)){
            throw new BusinessRuleException(AuthMessageUtil.EMAIL_OR_PASSWORD_INCORRECT);
        }
    }

    public void checkEmailAlreadyInUse(String registerEmail){
        if (userRepository.findByEmail(registerEmail) != null) {
            throw new BusinessRuleException(AuthMessageUtil.EMAIL_ALREADY_IN_USE);
        }
    }

    public void checkPasswordIsEmpty(String registerPassword){
        if(registerPassword == null || registerPassword.isEmpty()){
            throw new BusinessRuleException(AuthMessageUtil.PASSWORD_IS_EMPTY);
        }
    }

}
