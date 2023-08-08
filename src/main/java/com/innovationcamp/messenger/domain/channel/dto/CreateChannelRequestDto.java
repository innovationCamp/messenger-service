package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "새 채널 생성 request Dto")
public class CreateChannelRequestDto {
    private final String channelName;

    private final String channelPassword;

    private final String channelDescription;

    public CreateChannelRequestDto(String channelName, String channelPassword, String channelDescription) {
        this.channelName = channelName;
        this.channelPassword = channelPassword;
        this.channelDescription = channelDescription;
    }

}
