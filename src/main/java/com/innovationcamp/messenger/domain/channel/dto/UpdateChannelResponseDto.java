package com.innovationcamp.messenger.domain.channel.dto;

import lombok.Getter;

@Getter
public class UpdateChannelResponseDto {
    private final Long id;
    private final String channelName;
    private final String channelDescription;

    public UpdateChannelResponseDto(Long id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}