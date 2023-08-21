package com.innovationcamp.messenger.domain.channel.dto;


import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.message.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "ChannelContent 조회 response Dto")
public class GetChannelContentResponseDto {
    private final Long id;
    private final Long userId;
    private final String userEmail;
    private final String userName;
    private Long channelId;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;

    public GetChannelContentResponseDto(ChannelContent channelContent) {
        this.id = channelContent.getId();
        this.userId = channelContent.getUser().getId();
        this.userEmail = channelContent.getUser().getEmail();
        this.userName = channelContent.getUser().getUsername();
        this.channelId = channelContent.getChannel().getId();
        this.callOutContentId = channelContent.getCalloutContent() != null ? channelContent.getCalloutContent().getId() : null;
        this.createdAt = channelContent.getCreatedAt();
        this.notReadCount = channelContent.getNotReadCount();
    }

    public GetChannelContentResponseDto(Message message) {
        this.id = message.getId();
        this.userId = message.getUser().getId();
        this.userEmail = message.getUser().getEmail();
        this.userName = message.getUser().getUsername();
        this.channelId = message.getChannel().getId();
        this.callOutContentId = message.getCalloutContent() != null ? message.getCalloutContent().getId() : null;
        this.createdAt = message.getCreatedAt();
        this.notReadCount = message.getNotReadCount();
    }

}
