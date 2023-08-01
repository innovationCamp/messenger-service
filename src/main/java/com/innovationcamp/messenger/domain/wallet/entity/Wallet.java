package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long money;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
