package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "채널 수정 response Dto")
public class UpdateChannelResponseDto {
    private final Long channelId;
    private final String channelName;
    private final String channelDescription;

    public UpdateChannelResponseDto(Long channelId, String channelName, String channelDescription) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
