package com.innovationcamp.messenger.domain.message.service;

import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    @NonNull
    private final MessageRepository messageRepository;
}
