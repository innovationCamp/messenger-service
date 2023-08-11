package com.innovationcamp.messenger.domain.message.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TestMessageDto {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type; // 메시지 타입
    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}