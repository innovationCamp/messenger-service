package com.innovationcamp.messenger.domain.testmessage.service;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestMessageService {
    @NonNull
    private final UserChannelRepository userChannelRepository;
    @NonNull
    private final ChannelRepository channelRepository;

    public List<TestMessageRoomDto> findAllRoom(User user) {
        List<UserChannel> userChannelList = user.getUserChannelList();
        return userChannelList.stream().map(userChannel -> new TestMessageRoomDto(userChannel.getChannel())).toList();
    }

    public TestMessageRoomDto findRoomById(String roomId) {
        return new TestMessageRoomDto(channelRepository.findById(Long.valueOf(roomId)).orElseThrow(() -> new IllegalArgumentException("없는 채팅")));
    }

    public TestMessageRoomDto createTestMessageRoomDto(User user, String name) {
        Channel channel = new Channel(name);
        channelRepository.save(channel);
        UserChannel userChannel = new UserChannel(user, channel);
        userChannelRepository.save(userChannel);
        return new TestMessageRoomDto(channel);
    }
}
