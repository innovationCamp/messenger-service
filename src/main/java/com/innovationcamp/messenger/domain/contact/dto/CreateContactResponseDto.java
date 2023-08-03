package com.innovationcamp.messenger.domain.contact.dto;

import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CreateContactResponseDto {
    private String username;
    private String contactname;

    public CreateContactResponseDto(String username, User contactUser) {
        this.username = username;
        this.contactname = contactUser.getUsername();
    }
}
