package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.wallet.entity.GroupWallet;
import lombok.Getter;

@Getter
public class GroupWalletResponseDto {
    private Long groupWalletId;
    private Long money;
    private String walletName;
    private String description;
    private Long channel;

    public GroupWalletResponseDto(GroupWallet groupWallet){
        this.groupWalletId = groupWallet.getId();
        this.money = groupWallet.getMoney();
        this.walletName = groupWallet.getWalletName();
        this.description = groupWallet.getDescription();
        this.channel = groupWallet.getChannel().getId();
    }
}
