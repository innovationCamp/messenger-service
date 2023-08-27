package com.innovationcamp.messenger.domain.message.service;

import com.innovationcamp.messenger.domain.channel.dto.MessageContentResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.message.dto.*;
import com.innovationcamp.messenger.domain.message.entity.Message;
import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserChannelRepository userChannelRepository;

    public MessageContentResponseDto createMessage(MessageRequestDto requestDto) {
        // 채팅방 입장시, 토큰 검증을 하고 사용자 이름을 반환함
        // 이후 메세지 보낼때는 검증없이 사용자 이름으로 보냄
        // 현재는 연관관계설정을 위해 사용자 이름으로 user를 찾아서 넣어줌
        // 이후 NoSQL로 넘어가면 이 과정 없이 이름으로 넣을예정
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("없는 유저 입니다."));
        Channel channel = channelRepository.findById(requestDto.getChannelId())
                .orElseThrow(() -> new EntityNotFoundException("없는 채널 입니다."));

        // 전체 유저 -1
        Long notReadCount = userChannelRepository.countByChannelId(requestDto.getChannelId()) - 1L;

        Message.MessageBuilder messageBuilder = Message.builder();
        Message message;
        if (requestDto.getCallOutId() != null) {
            ChannelContent callOutContent = channelContentRepository.findById(requestDto.getCallOutId())
                    .orElseThrow(() -> new EntityNotFoundException("없는 메세지입니다."));
            message = messageBuilder
                    .user(user)
                    .channel(channel)
                    .notReadCount(notReadCount)
                    .callOutContent(callOutContent)
                    .message(requestDto.getMessage())
                    .build();
        } else {
            message = messageBuilder
                    .user(user)
                    .channel(channel)
                    .notReadCount(notReadCount)
                    .message(requestDto.getMessage())
                    .build();
        }
        return new MessageContentResponseDto(messageRepository.save(message));
    }

    public DeleteMessageResponseDto deleteMessage(DeleteMessageRequestDto requestDto) {
        ChannelContent channelContent = channelContentRepository.findById(requestDto.getChannelContentId())
                .orElseThrow(() -> new IllegalArgumentException("없는 컨텐트 입니다."));
        if (!requestDto.getSender().equals(channelContent.getUser().getUsername())) {
            throw new IllegalArgumentException("작성자가 아니면 삭제할 수 없습니다.");
        }

        channelContentRepository.delete(channelContent);

        return DeleteMessageResponseDto.builder()
                .channelId(requestDto.getChannelId())
                .channelContentId(requestDto.getChannelContentId())
                .build();
    }

    public List<MessageResponseDto> getAllMessage(Long roomId) {
        return null;

//        return messageRepository.findAllByChannelId(roomId).stream()
//                .map(MessageResponseDto::new)
//                .toList();
    }

    public TestMessageRoomUserDto getRoomAndUserInfo(Long roomId, User user) {
        Channel channel = channelRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("없는 채팅"));
        boolean firstEntry = !userChannelRepository.existsByChannelIdAndUserId(channel.getId(), user.getId());
        return new TestMessageRoomUserDto(channel, user.getUsername(),firstEntry);
    }
}
