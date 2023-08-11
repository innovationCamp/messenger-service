package com.innovationcamp.messenger.domain.message.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.entity.Message;
import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import com.innovationcamp.messenger.domain.message.dto.TestMessageDto;
import com.innovationcamp.messenger.domain.message.dto.TestMessageRoomUserDto;
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

    public void testCreateMessage(TestMessageDto messageDto) {
        // 채팅방 입장시, 토큰 검증을 하고 사용자 이름을 반환함
        // 이후 메세지 보낼때는 검증없이 사용자 이름으로 보냄
        // 현재는 연관관계설정을 위해 사용자 이름으로 user를 찾아서 넣어줌
        // 이후 NoSQL로 넘어가면 이 과정 없이 이름으로 넣을예정
        User user = userRepository.findByUsername(messageDto.getSender()).orElseThrow(() -> new EntityNotFoundException("없는 유저 입니다."));
        Channel channel = channelRepository.findById(messageDto.getRoomId()).orElseThrow(() -> new EntityNotFoundException("없는 채널 입니다."));
        Message message = new Message(user, channel, messageDto.getMessage());
        messageRepository.save(message);
    }

    public List<MessageResponseDto> getAllMessage(Long roomId) {
        // 제한사항
        // 1. 현재 재입장시 그 채널에 모든 메세지 반환 -> 유저가 초대되기 전의 메세지들도 반환됨
        // userChannel에 입장 시점이 있었으면 좋겠음

        // 2. 메세지만 생각하면 문제 없으나, 파일까지 생각하면 머리아픔

        return messageRepository.findAllByChannelId(roomId).stream().map(MessageResponseDto::new).toList();
    }

    public TestMessageRoomUserDto getRoomAndUserInfo(Long roomId, User user) {
        Channel channel = channelRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("없는 채팅"));
        boolean firstEntry = !userChannelRepository.existsByChannelIdAndUserId(channel.getId(), user.getId());
        return new TestMessageRoomUserDto(channel, user.getUsername(),firstEntry);
    }
}
