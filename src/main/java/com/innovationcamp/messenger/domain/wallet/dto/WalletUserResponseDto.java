package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.UserAuthorityEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WalletUserResponseDto {
    private String email;
    private String username;
    private UserAuthorityEnum userAuthorityEnum;

    public WalletUserResponseDto(User user, UserAuthorityEnum userAuthorityEnum) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.userAuthorityEnum = userAuthorityEnum;
    }
}
