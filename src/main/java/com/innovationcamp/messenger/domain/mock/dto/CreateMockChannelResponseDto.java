package com.innovationcamp.messenger.domain.mock.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import lombok.Getter;

@Getter
public class CreateMockChannelResponseDto {
    private final Long channelId;
    private final String channelCreateUsername;
    private final String channelName;
    private final String channelDescription;
    private final Boolean isPrivate;

    public CreateMockChannelResponseDto(Channel channel){
        this.channelId = channel.getId();
        this.channelCreateUsername = channel.getChannelCreateUser().getUsername();
        this.channelName = channel.getChannelName();
        this.channelDescription = channel.getChannelDescription();
        this.isPrivate = channel.getIsPrivate();
    }
}
