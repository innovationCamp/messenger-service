package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "새 채널 생성 response Dto")
@Getter
public class CreateChannelResponseDto {
    private final Long channelId;
    private final String channelCreateUsername;
    private final String channelName;
    private final String channelDescription;
    private final Boolean isPrivate;
    public CreateChannelResponseDto(Long channelId,
                                    String channelCreateUsername,
                                    String channelName,
                                    String channelDescription,
                                    Boolean isPrivate) {
        this.channelId = channelId;
        this.channelCreateUsername = channelCreateUsername;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.isPrivate = isPrivate;
    }

    public CreateChannelResponseDto(Channel channel){
        this.channelId = channel.getId();
        this.channelCreateUsername = channel.getChannelCreateUser().getUsername();
        this.channelName = channel.getChannelName();
        this.channelDescription = channel.getChannelDescription();
        this.isPrivate = channel.getIsPrivate();
    }
}
