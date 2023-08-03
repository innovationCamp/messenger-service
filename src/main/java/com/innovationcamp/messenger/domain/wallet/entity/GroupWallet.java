package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class GroupWallet extends Wallet {
    @Column
    private String walletName;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AuthorityEnum authority;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;
}
