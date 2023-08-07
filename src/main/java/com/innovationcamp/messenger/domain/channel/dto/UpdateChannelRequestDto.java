package com.innovationcamp.messenger.domain.channel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateChannelRequestDto {
    private final String channelName;
    private final String channelPassword;
    private final String channelDescription;

    public UpdateChannelRequestDto(String channelName, String password, String channelDescription) {
        this.channelName = channelName;
        this.channelPassword = password;
        this.channelDescription = channelDescription;
    }
}
