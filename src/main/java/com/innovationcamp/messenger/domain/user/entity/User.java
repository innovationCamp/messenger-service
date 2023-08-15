package com.innovationcamp.messenger.domain.user.entity;

import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column
    private String username;
    @Column
    private String password;

    @Builder
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void mockUser(String encodedPassword) {
        this.email = "mockMail"+this.id;
        this.username = "mockName"+this.id;
        this.password = encodedPassword;
    }

    public void update(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof User user) && this.id.equals(user.getId()) && this.email.equals(user.getEmail())
                && this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
    }
}
