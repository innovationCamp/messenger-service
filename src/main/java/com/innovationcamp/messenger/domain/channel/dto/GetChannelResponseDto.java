package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "채널 한 개의 정보 조회 response Dto")
public class GetChannelResponseDto {
    private final Long channelId;
    private final String channelCreateUserName;
    private final String channelName;
    private final String channelDescription;
    private final LocalDateTime createdAt;

    public GetChannelResponseDto(Long channelId, String channelCreateUserName, String channelName, String channelDescription, LocalDateTime createdAt) {
        this.channelId = channelId;
        this.channelCreateUserName = channelCreateUserName;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.createdAt = createdAt;
    }
}
