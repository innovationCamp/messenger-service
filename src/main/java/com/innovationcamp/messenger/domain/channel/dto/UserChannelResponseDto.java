package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Schema(description = "유저가 가입한 채널 조회 response Dto")
public class UserChannelResponseDto {

    private final Long channelId;
    private final LocalDateTime readTimestamp;
    private final boolean isAdmin;

    public UserChannelResponseDto(Long channelId, LocalDateTime readTimestamp, boolean isAdmin) {
        this.channelId = channelId;
        this.readTimestamp = readTimestamp;
        this.isAdmin = isAdmin;
    }
}
