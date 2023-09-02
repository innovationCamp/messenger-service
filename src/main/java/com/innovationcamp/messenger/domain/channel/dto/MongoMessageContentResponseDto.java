package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.message.mongodb.entity.MongoMessage;
import lombok.Getter;

@Getter
public class MongoMessageContentResponseDto extends GetMongoChannelContentResponseDto {
    private final String text;

    public MongoMessageContentResponseDto(MongoMessage message) {
        super(message);
        this.text = message.getText();
    }
}
