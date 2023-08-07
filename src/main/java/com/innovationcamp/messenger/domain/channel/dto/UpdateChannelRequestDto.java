package com.innovationcamp.messenger.domain.channel.dto;

import lombok.Getter;

@Getter
public class UpdateChannelRequestDto {
    private final String channelName;
    private final String password;
    private final String channelDescription;

    public UpdateChannelRequestDto(String channelName, String password, String channelDescription) {
        this.channelName = channelName;
        this.password = password;
        this.channelDescription = channelDescription;
    }
}
