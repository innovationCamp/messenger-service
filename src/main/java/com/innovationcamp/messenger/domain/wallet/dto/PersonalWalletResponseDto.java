package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.wallet.entity.PersonalWallet;

public class PersonalWalletResponseDto {
    private Long walletId;
    private Long money;
    private String username;

    public PersonalWalletResponseDto(PersonalWallet personalWallet){
        this.walletId = personalWallet.getId();
        this.money = personalWallet.getMoney();
        this.username = personalWallet.getUser().getUsername();
    }
}
