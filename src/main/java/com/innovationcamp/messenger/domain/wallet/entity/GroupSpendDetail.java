package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class GroupSpendDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "group_wallet_id")
    private GroupWallet groupWallet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public GroupSpendDetail(Transaction transaction, GroupWallet groupWallet, User user) {
        this.transaction = transaction;
        this.groupWallet = groupWallet;
        this.user = user;
    }
}
