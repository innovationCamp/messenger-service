package com.innovationcamp.messenger.domain.testmessage.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.message.entity.Message;
import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageDto;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageResponseDto;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomUserDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestMessageService {
    @NonNull
    private final UserChannelRepository userChannelRepository;
    @NonNull
    private final ChannelRepository channelRepository;
    @NonNull
    private final MessageRepository messageRepository;
    @NonNull
    private final ChannelContentRepository channelContentRepository;

    public List<TestMessageRoomDto> findAllRoom(User user) {
        List<UserChannel> userChannelList = user.getUserChannelList();
        return userChannelList.stream().map(userChannel -> new TestMessageRoomDto(userChannel.getChannel())).toList();
    }

    public TestMessageRoomUserDto findRoomById(Long roomId, User user) {
        Channel channel = channelRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("없는 채팅"));
        boolean firstEntry = !userChannelRepository.existsByChannelIdAndUserId(channel.getId(), user.getId());
        return new TestMessageRoomUserDto(channel, user.getUsername(),firstEntry);
    }

    public TestMessageRoomDto createTestMessageRoomDto(User user, String name) {
        Channel channel = new Channel(name);
        channelRepository.save(channel);
        UserChannel userChannel = new UserChannel(user, channel);
        userChannelRepository.save(userChannel);
        return new TestMessageRoomDto(channel);
    }

    public List<TestMessageResponseDto> getAllMessage(Long roomId) {
        // 제한사항
        // 1. 현재 재입장시 그 채널에 모든 메세지 반환 -> 유저가 초대되기 전의 메세지들도 반환됨
        // userChannel에 입장 시점이 있었으면 좋겠음

        // 2. 메세지만 생각하면 문제 없으나, 파일까지 생각하면 머리아픔
//        List<ChannelContent> channelContentList = channelContentRepository.findAllByChannelId(roomId);
        return messageRepository.findAllByChannelId(roomId).stream().map(TestMessageResponseDto::new).toList();
    }
}
