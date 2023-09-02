package com.innovationcamp.messenger.domain.message.mongodb.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "message")
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class MongoMessage extends MongoChannelContent{
    private String text;
    @Builder
    public MongoMessage(Long offsetId, Long channelId, Long userId, String userName, Long calloutContentId, Long notReadCount, String message, LocalDateTime createdAt) {
        super(offsetId, channelId, userId, userName, calloutContentId, notReadCount, createdAt);
        this.text = message;
    }
}
