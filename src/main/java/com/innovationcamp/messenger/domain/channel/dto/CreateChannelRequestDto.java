package com.innovationcamp.messenger.domain.channel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "새 채널 생성 request Dto")
public class CreateChannelRequestDto {
    private String channelName;
    private String channelPassword;
    private String channelDescription;
    private Boolean isPrivate;

    public CreateChannelRequestDto(String channelName,
                                   String channelPassword,
                                   String channelDescription,
                                   Boolean isPrivate) {
        this.channelName = channelName;
        this.channelPassword = channelPassword;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }

}
