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
    private final String userEmail;
    private final String userName;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;

    public GetChannelContentResponseDto(ChannelContent channelContent) {
        this.id = channelContent.getId();
        this.userEmail = channelContent.getUser().getEmail();
        this.userName = channelContent.getUser().getUsername();
        this.callOutContentId = channelContent.getCalloutContent() != null ? channelContent.getCalloutContent().getId() : null;
        this.createdAt = channelContent.getCreatedAt();
        this.notReadCount = channelContent.getNotReadCount();
    }

    public GetChannelContentResponseDto(Message message) {
        this.id = message.getId();
        this.userEmail = message.getUser().getEmail();
        this.userName = message.getUser().getUsername();
        this.callOutContentId = message.getCalloutContent() != null ? message.getCalloutContent().getId() : null;
        this.createdAt = message.getCreatedAt();
        this.notReadCount = message.getNotReadCount();
    }

}
