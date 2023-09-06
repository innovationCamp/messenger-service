package com.innovationcamp.messenger.domain.message.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageRequestDto {
    // 메시지 타입 : 입장, 채팅, CALL OUT
    // 프론트에서 정해주는 값입니다.
    public enum MessageType {
        ENTER, TALK, CALLOUT
    }
    private MessageType type; // 메시지 타입
    private Long channelId; // 방번호
    private Long senderId; // 메시지 보낸사람
    private String senderName; // 메시지 보낸사람
    private String message; // 메시지
    private Long callOutId;
    private LocalDateTime createdAt;
}