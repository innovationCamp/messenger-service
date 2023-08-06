package com.innovationcamp.messenger.domain.channel.dto;

public class ChannelDto {
    private Long id;
    private String channelName;
    private String channelDescription;
    public ChannelDto(Long id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
