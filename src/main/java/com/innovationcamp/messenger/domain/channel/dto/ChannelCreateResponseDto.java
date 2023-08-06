package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class ChannelCreateResponseDto {
    private Long id;
    private String channelName;
    private String channelDescription;
    public ChannelCreateResponseDto(Long id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
