package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import lombok.Getter;
import lombok.ToString;

@Getter
public class SignUpChannelRequestDto {
    String userName;
    String userEmail;
    String channelPassword;
}
