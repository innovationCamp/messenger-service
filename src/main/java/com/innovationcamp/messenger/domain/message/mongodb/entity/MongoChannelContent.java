package com.innovationcamp.messenger.domain.message.mongodb.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "message")
@Getter
@NoArgsConstructor
@ToString
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MongoChannelContent {
    @Id
    private String id;
    private Long channelId;
    private Long userId;
    private String userName;
    private Long calloutContentId;
    private Long notReadCount;
    private LocalDateTime createdAt;

    public MongoChannelContent(Long channelId, Long userId, String userName, Long calloutContentId, Long notReadCount, LocalDateTime createdAt){
        this.channelId = channelId;
        this.userId = userId;
        this.userName = userName;
        this.calloutContentId = calloutContentId;
        this.notReadCount = notReadCount;
        this.createdAt = createdAt;
    }
}
