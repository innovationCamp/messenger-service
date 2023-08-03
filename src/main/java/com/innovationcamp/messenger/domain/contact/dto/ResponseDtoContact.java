package com.innovationcamp.messenger.domain.contact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDtoContact {
    private String email;
    private String username;
    private String contactname;

    public ResponseDtoContact(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public ResponseDtoContact(String username, User contactUser) {
        this.username = username;
        this.contactname = contactUser.getUsername();
    }
}
