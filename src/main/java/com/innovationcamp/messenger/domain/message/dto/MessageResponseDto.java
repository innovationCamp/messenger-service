package com.innovationcamp.messenger.domain.message.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Getter;

@Getter
public class MessageResponseDto {
    private Long id;
    private String text;

    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.text = message.getText();
    }
}
