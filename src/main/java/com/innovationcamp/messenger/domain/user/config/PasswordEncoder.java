package com.innovationcamp.messenger.domain.user.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    public String encode(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String password, String encodedPassword){
        return BCrypt.checkpw(password, encodedPassword);
    }
}
