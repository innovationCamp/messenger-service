package com.innovationcamp.messenger.domain.user.dto;

import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String email;
    private String username;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
