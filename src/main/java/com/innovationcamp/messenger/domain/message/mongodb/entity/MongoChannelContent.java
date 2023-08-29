package com.innovationcamp.messenger.domain.message.mongodb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
@Getter
@NoArgsConstructor
@ToString
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MongoChannelContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long channelId;
    private Long userId;
    private String userName;
    private Long calloutContentId;
    private Long notReadCount;

    public MongoChannelContent(Long channelId, Long userId, String userName, Long calloutContentId, Long notReadCount){
        this.channelId = channelId;
        this.userId = userId;
        this.userName = userName;
        this.calloutContentId = calloutContentId;
        this.notReadCount = notReadCount;
    }
}
