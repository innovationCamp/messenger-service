package com.innovationcamp.messenger.domain.wallet.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GroupWalletCreateDto {
    private Long channelId;
    private String walletName;
    private String description;
    private String password;

    public void setPassword(String password){
        this.password = password;
    }
}
