package com.innovationcamp.messenger.domain.mock.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateMockChannelResponseDto {
    private final Long channelId;
    private final String channelName;
    private final String channelCreateUsername;
    private final Long userCount;
    private final Boolean isPrivate;
    private final String channelDescription;
    private final LocalDateTime createdAt;


    public CreateMockChannelResponseDto(Channel channel, Long userCount){
        this.channelId = channel.getId();
        this.channelName = channel.getChannelName();
        this.channelCreateUsername = channel.getChannelCreateUser().getUsername();
        this.userCount = userCount;
        this.isPrivate = channel.getIsPrivate();
        this.channelDescription = channel.getChannelDescription();
        this.createdAt = channel.getCreatedAt();
    }
}
