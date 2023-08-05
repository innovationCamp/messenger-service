package com.innovationcamp.messenger.domain.message.controller;

import com.innovationcamp.messenger.domain.message.service.MessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    @NonNull
    private final MessageService messageService;
}
