package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.service.MessageService;
import com.innovationcamp.messenger.domain.message.dto.TestMessageDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    @NonNull
    private final MessageService messageService;
    @NonNull
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/{channelId}")
    public MessageResponseDto createMessage(@RequestAttribute User user,
                                            @PathVariable Long channelId,
                                            @RequestBody MessageRequestDto requestDto){
        return messageService.createMessage(user, channelId, requestDto);
    }

    @DeleteMapping("/{messageId}")
    public String deleteMessage(@RequestAttribute User user,
                                @PathVariable Long messageId){
        return messageService.deleteMessage(user, messageId);
    }

    @MessageMapping("/chat/message") // requestMapping이랑 무관하게 작동 // RESTful 한가?
    @Async // 비동기적 처리
    public void message(TestMessageDto message) {
        if (TestMessageDto.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        messageService.testCreateMessage(message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
