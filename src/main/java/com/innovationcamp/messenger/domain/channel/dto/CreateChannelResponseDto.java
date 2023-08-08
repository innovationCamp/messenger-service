package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class CreateChannelResponseDto {
    private final Long channelId;
    private final String channelCreatedUserEmail;
    private final String channelName;
    private final String channelDescription;
    public CreateChannelResponseDto(Long id, String channelCreatedUserEmail,String channelName, String channelDescription) {
        this.channelId = id;
        this.channelCreatedUserEmail = channelCreatedUserEmail;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
