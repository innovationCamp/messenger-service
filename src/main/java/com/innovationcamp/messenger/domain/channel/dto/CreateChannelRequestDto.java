package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Schema(description = "새 채널 생성 request Dto")
public class CreateChannelRequestDto {
    private String channelName;
    private String channelPassword;
    private String channelDescription;
    private Boolean isPrivate;
}
