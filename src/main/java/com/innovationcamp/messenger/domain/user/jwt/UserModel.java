package com.innovationcamp.messenger.domain.user.jwt;

import lombok.Getter;

@Getter
public class UserModel {
    private String email;
    private String username;

    public UserModel(String email, String username){
        this.email = email;
        this.username = username;
    }
}
