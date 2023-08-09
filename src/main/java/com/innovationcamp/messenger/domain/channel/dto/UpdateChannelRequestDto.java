package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Schema(description = "채널 수정 request Dto")
public class UpdateChannelRequestDto {
    private final String channelName;

    private final String channelDescription;

    public UpdateChannelRequestDto(String channelName, String channelDescription) {
        this.channelName = channelName;

        this.channelDescription = channelDescription;
    }
}
