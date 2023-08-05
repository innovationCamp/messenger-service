package com.innovationcamp.messenger.domain.wallet.entity;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.dto.GroupWalletCreateDto;
import jakarta.persistence.*;
import lombok.Builder;
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

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @Builder
    public GroupWallet(Long money, User user, Channel channel, GroupWalletCreateDto requestDto) {
        super(money, requestDto.getPassword(), user);
        this.walletName = requestDto.getWalletName();
        this.description = requestDto.getDescription();
        this.channel = channel;
    }
}
