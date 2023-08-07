package com.innovationcamp.messenger.domain.testmessage.controller;

import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test/message")
public class TestMessageController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message") // requestMapping이랑 무관하게 작동, 따라서 @RequestMapping("/api/test/message") 사실상 필요없음
    public void message(TestMessageDto message) {
        if (TestMessageDto.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}