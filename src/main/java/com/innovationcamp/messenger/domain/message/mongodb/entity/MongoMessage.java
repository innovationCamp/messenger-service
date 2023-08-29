package com.innovationcamp.messenger.domain.message.mongodb.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class MongoMessage extends MongoChannelContent{
    private String text;
    @Builder
    public MongoMessage(Long channelId, Long userId, String userName, Long calloutContentId, Long notReadCount, String text) {
        super(channelId, userId, userName, calloutContentId, notReadCount);
        this.text = text;
    }
}
