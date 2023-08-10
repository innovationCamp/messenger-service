package com.innovationcamp.messenger.domain.testmessage.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Getter;

@Getter
public class TestMessageResponseDto {
    private String sender; // 메시지 보낸사람
    private String message; // 메시지

    public TestMessageResponseDto(Message message) {
        this.sender = message.getUser().getUsername();
        this.message = message.getText();
    }
}
