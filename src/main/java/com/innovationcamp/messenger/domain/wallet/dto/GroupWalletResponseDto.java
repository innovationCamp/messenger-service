package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import lombok.Getter;

@Getter
public class GroupWalletResponseDto {
    private boolean admin;
    private Long money;
    private String walletName;
    private String description;
    private Long channel;

    public GroupWalletResponseDto(GroupWallet groupWallet){
        this.admin = groupWallet.isAdmin();
        this.money = groupWallet.getMoney();
        this.walletName = groupWallet.getWalletName();
        this.description = groupWallet.getDescription();
        this.channel = groupWallet.getChannel().getId();
    }
}
