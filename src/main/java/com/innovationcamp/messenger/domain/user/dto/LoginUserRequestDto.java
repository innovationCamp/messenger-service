package com.innovationcamp.messenger.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginUserRequestDto {
    private String email;
    private String password;
}
