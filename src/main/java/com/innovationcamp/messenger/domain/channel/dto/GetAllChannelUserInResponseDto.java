package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "유저가 가입한 채널 조회 response Dto")
public class GetAllChannelUserInResponseDto {

    private final Long channelId;
    private final String channelName;
    private final LocalDateTime readTimestamp;
    private final boolean isAdmin;

    public GetAllChannelUserInResponseDto(Long channelId, String channelName, LocalDateTime readTimestamp, boolean isAdmin) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.readTimestamp = readTimestamp;
        this.isAdmin = isAdmin;
    }
}
