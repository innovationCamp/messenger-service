package com.innovationcamp.messenger.domain.channel.dto;


import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoChannelContent;
import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "MongoChannelContent 조회 response Dto")
public class GetMongoChannelContentResponseDto {
    private final String  id;
    private final Long userId;
//    private final String userEmail;
    private final String userName;
    private Long channelId;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;

    public GetMongoChannelContentResponseDto(MongoChannelContent channelContent) {
        this.id = channelContent.getId();
        this.userId = channelContent.getUserId();
//        this.userEmail = channelContent.getUser().getEmail();
        this.userName = channelContent.getUserName();
        this.channelId = channelContent.getChannelId();
        this.callOutContentId = channelContent.getCalloutContentId() != null ? channelContent.getCalloutContentId() : null;
        this.createdAt = channelContent.getCreatedAt();
        this.notReadCount = channelContent.getNotReadCount();
    }

    public GetMongoChannelContentResponseDto(MongoMessage message) {
        this.id = message.getId();
        this.userId = message.getUserId();
//        this.userEmail = message.getUser().getEmail();
        this.userName = message.getUserName();
        this.channelId = message.getChannelId();
        this.callOutContentId = message.getCalloutContentId() != null ? message.getCalloutContentId() : null;
        this.createdAt = message.getCreatedAt();
        this.notReadCount = message.getNotReadCount();
    }
}
