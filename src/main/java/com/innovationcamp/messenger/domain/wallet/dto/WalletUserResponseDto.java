package com.innovationcamp.messenger.domain.wallet.dto;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.wallet.entity.AuthorityEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WalletUserResponseDto {
    private String email;
    private String username;
    private AuthorityEnum authorityEnum;
    public WalletUserResponseDto(User user, AuthorityEnum authorityEnum) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.authorityEnum = authorityEnum;
    }
}
