package com.innovationcamp.messenger.domain.message.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto {
    private Long id;
    private Long userId;
    private String userEmail;
    private String userName;
    private Long channelId;
    private Long callOutContentId;
    private LocalDateTime createdAt;
    private Long notReadCount;
    private String text;
    private MessageRequestDto.MessageType type;

    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.userId = message.getUser().getId();
        this.userEmail = message.getUser().getEmail();
        this.userName = message.getUser().getUsername();
        this.channelId = message.getChannel().getId();
        this.callOutContentId = message.getCalloutContent() != null ? message.getCalloutContent().getId() : null;
        this.createdAt = message.getCreatedAt();
        this.notReadCount = message.getNotReadCount();
        this.text = message.getText();
    }
}
