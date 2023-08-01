package com.innovationcamp.messenger.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;

    /*
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
     */
}
