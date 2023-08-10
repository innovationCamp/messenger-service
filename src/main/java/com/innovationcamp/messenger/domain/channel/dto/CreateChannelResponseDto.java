package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class CreateChannelResponseDto {
    private final Long channelId;
    private final Long channelCreateUserId;
    private final String channelName;
    private final String channelDescription;
    private final Boolean isPrivate;
    public CreateChannelResponseDto(Long channelId,
                                    Long channelCreatedUserId,
                                    String channelName,
                                    String channelDescription,
                                    Boolean isPrivate) {
        this.channelId = channelId;
        this.channelCreateUserId = channelCreatedUserId;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }
}
