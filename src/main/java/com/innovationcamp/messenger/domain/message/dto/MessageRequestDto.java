package com.innovationcamp.messenger.domain.message.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MessageRequestDto {
    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK, CALLOUT
    }
    private MessageType type; // 메시지 타입
    private Long channelId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private Long callOutId;
}