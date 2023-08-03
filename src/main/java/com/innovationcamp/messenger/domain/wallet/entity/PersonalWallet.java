package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
public class PersonalWallet extends Wallet {
    public PersonalWallet(boolean admin, Long money, String password, User user) {
        super(admin, money, password, user);
    }
}
