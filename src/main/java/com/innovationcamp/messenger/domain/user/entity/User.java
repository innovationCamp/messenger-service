package com.innovationcamp.messenger.domain.user.entity;

import com.innovationcamp.messenger.domain.user.dto.CreateUserRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    /*
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
     */
}
