package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Getter;

@Getter
public class MessageContentResponseDto extends GetChannelContentResponseDto {
    private final String text;

    public MessageContentResponseDto(Message message) {
        super(message);
        this.text = message.getText();
    }
}
