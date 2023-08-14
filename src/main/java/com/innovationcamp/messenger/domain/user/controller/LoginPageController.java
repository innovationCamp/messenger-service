package com.innovationcamp.messenger.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class LoginPageController {
    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }
}
