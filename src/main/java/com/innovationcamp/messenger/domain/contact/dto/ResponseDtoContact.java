package com.innovationcamp.messenger.domain.contact.dto;

import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;

@Getter
public class ResponseDtoContact {
    private String email;
    private String username;

    public ResponseDtoContact(String email, String username) {
        this.email = email;
        this.username = username;
    }


    public ResponseDtoContact(User contactUser) {
        this.email = contactUser.getEmail();
        this.username = contactUser.getUsername();
    }
}
