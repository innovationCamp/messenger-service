package com.innovationcamp.messenger.domain.user.jwt;

import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class UserModel {
    private Long id;
    private String email;
    private String username;

    public UserModel(Claims info){
        this.id = Long.valueOf(info.getSubject());
        this.email = info.get(JwtUtil.EMAIL_KEY, String.class);
        this.username = info.get(JwtUtil.NICKNAME_KEY, String.class);
    }
}
