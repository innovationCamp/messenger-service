package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class CreateChannelResponseDto {
    private final Long id;
    private final String channelName;
    private final String channelDescription;
    public CreateChannelResponseDto(Long id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
