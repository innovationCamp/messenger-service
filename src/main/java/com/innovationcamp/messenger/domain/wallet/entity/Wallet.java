package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Wallet extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long money;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wallet(Long money, String password, User user){
        this.money = money;
        this.password = password;
        this.user = user;
    }

    public void update(Long balance) {
        this.money = balance;
    }
}
