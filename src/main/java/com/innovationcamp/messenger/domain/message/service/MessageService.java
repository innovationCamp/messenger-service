package com.innovationcamp.messenger.domain.message.service;

import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    @NonNull
    private MessageRepository messageRepository;

    public String createMessage(Long channelId) {
        return "success";
    }

    public String deleteMessage(Long messageId) {
        return "success";
    }
}
