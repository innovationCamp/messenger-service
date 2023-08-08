package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class CreateChannelResponseDto {
    private final Long channelId;
    private final String channelCreateUserName;
    private final String channelName;
    private final String channelDescription;
    private final Boolean isPrivate;
    public CreateChannelResponseDto(Long channelId,
                                    String channelCreatedUserName,
                                    String channelName,
                                    String channelDescription,
                                    Boolean isPrivate) {
        this.channelId = channelId;
        this.channelCreateUserName = channelCreatedUserName;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }
}
