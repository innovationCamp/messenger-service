package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.channel.dto.MessageContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.MongoMessageContentResponseDto;
import com.innovationcamp.messenger.domain.message.dto.*;
import com.innovationcamp.messenger.domain.message.service.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    @NonNull
    private final MessageService messageService;
    @NonNull
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    @Async // 비동기적 처리
    public void message(MessageRequestDto requestDto) {
        if (MessageRequestDto.MessageType.ENTER.equals(requestDto.getType())) {
            requestDto.setMessage(requestDto.getSenderName() + "님이 입장하셨습니다.");
        }
        LocalDateTime createdAt = LocalDateTime.now();
        requestDto.setCreatedAt(createdAt);
        MongoMessageContentResponseDto responseDto = messageService.saveMessage(requestDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getChannelId(), responseDto);
    }

    @PostMapping("/jmeter")
    @Async
    public void jmeterMessage(@RequestBody MessageRequestDto requestDto){
        if (MessageRequestDto.MessageType.ENTER.equals(requestDto.getType())) {
            requestDto.setMessage(requestDto.getSenderName() + "님이 입장하셨습니다.");
        }
        LocalDateTime createdAt = LocalDateTime.now();
        requestDto.setCreatedAt(createdAt);
        MongoMessageContentResponseDto responseDto = messageService.saveMessage(requestDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getChannelId(), responseDto);
    }

    @MessageMapping("/delete/message")
    @Async
    public void deleteMessage(DeleteMessageRequestDto requestDto) {

        DeleteMessageResponseDto responseDto = messageService.deleteMessage(requestDto);
        messagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getChannelId(), responseDto);
    }
}
