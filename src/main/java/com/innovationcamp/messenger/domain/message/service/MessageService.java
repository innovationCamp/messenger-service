package com.innovationcamp.messenger.domain.message.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.entity.Message;
import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
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
    @NonNull
    private final ChannelRepository channelRepository;
    @NonNull
    private final ChannelContentRepository channelContentRepository;

    public MessageResponseDto createMessage(User user, Long channelId, MessageRequestDto requestDto) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(()-> new IllegalArgumentException("없는 채널 입니다."));
        Message message = new Message(user, channel, requestDto);
        messageRepository.save(message);
        return new MessageResponseDto(message);
    }

    public String deleteMessage(User user, Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("없는 메세지 입니다."));
        if (!message.getUser().equals(user)) {
            throw new IllegalArgumentException("작성자가 아니면 삭제할 수 없습니다.");
        }
        messageRepository.delete(message);
        return "삭제 완료";
    }
}
