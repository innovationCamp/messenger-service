package com.innovationcamp.messenger.domain.exception.controller;

import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public UserModel userModel(HttpServletRequest req) {
        return (UserModel) req.getAttribute("userModel");
    }
}
