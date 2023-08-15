package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import lombok.Getter;

@Getter
public class SignUpChannelResponseDto {
    Long channelId;
    String channelName;
    String userName;
    String userEmail;

    public SignUpChannelResponseDto(UserChannel userChannel){
        this.channelId = userChannel.getChannel().getId();
        this.channelName = userChannel.getChannel().getChannelName();
        this.userName = userChannel.getUser().getUsername();
        this.userEmail = userChannel.getUser().getEmail();
    }
}

