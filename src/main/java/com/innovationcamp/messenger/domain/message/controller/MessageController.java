package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.service.MessageService;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    @NonNull
    private final MessageService messageService;

    @PostMapping("/{channelId}")
    public MessageResponseDto createMessage(@ModelAttribute User user,
                                            @PathVariable Long channelId,
                                            @RequestBody MessageRequestDto requestDto){
        return messageService.createMessage(user, channelId, requestDto);
    }
}
