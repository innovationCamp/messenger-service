package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.message.service.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @NonNull
    private MessageService messageService;

    @PostMapping("/{channelId}")
    public String createMessage(@PathVariable Long channelId){
        return messageService.createMessage(channelId);
    }

    @DeleteMapping("/{messageId}")
    public String deleteMessage(@PathVariable Long messageId){
        return messageService.deleteMessage(messageId);
    }
}
