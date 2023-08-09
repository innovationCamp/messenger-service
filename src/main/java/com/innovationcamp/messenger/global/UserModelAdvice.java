package com.innovationcamp.messenger.global;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserModelAdvice {

    @ModelAttribute
    public User user(HttpServletRequest req) {
        return (User) req.getAttribute("user");
    }
}
