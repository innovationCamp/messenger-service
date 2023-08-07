package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "채널 한 개의 정보 조회 response Dto")
public class ChannelSingleResponseDto {
    private Long id;
    private String channelName;
    private String channelDescription;

    public ChannelSingleResponseDto(Long id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }
}
