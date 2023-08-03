package com.innovationcamp.messenger.domain.contact.dto;

import lombok.Getter;

@Getter
public class ContactResponseDto {
    private String email;
    private String username;

    public ContactResponseDto(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
