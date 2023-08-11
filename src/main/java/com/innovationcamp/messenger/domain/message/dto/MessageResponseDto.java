package com.innovationcamp.messenger.domain.message.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto {
    private Long id;
    private String username;
    private String text;
    private Long channelId;
    private LocalDateTime createdAt;

    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.username = message.getUser().getUsername();
        this.text = message.getText();
        this.channelId = message.getChannel().getId();
        this.createdAt = message.getCreatedAt();
    }
}
