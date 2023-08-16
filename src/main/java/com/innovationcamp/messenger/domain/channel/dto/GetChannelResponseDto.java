package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "채널 한 개의 정보 조회 response Dto")
public class GetChannelResponseDto {
    private final Long channelId;
    private final String channelName;
    private final String channelCreateUserName;
    private final Long userCount;
    private final Boolean isPrivate;
    private final String channelDescription;
    private final LocalDateTime createdAt;

    public GetChannelResponseDto(Long channelId,
                                 String channelName,
                                 String channelCreateUserName,
                                 String channelDescription,
                                 LocalDateTime createdAt,
                                 Boolean isPrivate,
                                 Long userCount) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelCreateUserName = channelCreateUserName;
        this.channelDescription = channelDescription;
        this.createdAt = createdAt;
        this.isPrivate = isPrivate;
        this.userCount = userCount;
    }
}
