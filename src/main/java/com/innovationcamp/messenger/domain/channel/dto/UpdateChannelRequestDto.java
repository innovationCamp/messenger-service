package com.innovationcamp.messenger.domain.channel.dto;

import lombok.Getter;

@Getter
public class UpdateChannelRequestDto {
    private String channelName;
    private String password;
    private String channelDescription;

    public UpdateChannelRequestDto(String channelName, String password, String channelDescription) {
        this.channelName = channelName;
        this.password = password;
        this.channelDescription = channelDescription;
    }
}
