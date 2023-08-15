package com.innovationcamp.messenger.domain.mock.service;

import com.innovationcamp.messenger.domain.channel.config.ChannelPasswordEncoder;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.entity.Message;
import com.innovationcamp.messenger.domain.message.repository.MessageRepository;
import com.innovationcamp.messenger.domain.mock.dto.CreateMockChannelResponseDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MockService {
    @NonNull
    private final ChannelRepository channelRepository;
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserChannelRepository userChannelRepository;
    @NonNull
    private final ChannelPasswordEncoder pwEncoder;
    @NonNull
    private final MessageRepository messageRepository;

    private final EntityManager entityManager;

    @Transactional
    public List<CreateMockChannelResponseDto> createMockChannel(
            Boolean isPrivate,
            Long howManyChannel,
            Long howManyUserInChannel,
            Long howManyContentPerUser)
    {

        List<CreateMockChannelResponseDto> dtoList = new ArrayList<>();
        List<User> mockUserList = new ArrayList<>();
        String password = pwEncoder.encode("0000");

        // Mock User 생성
        for (int i = 0; i < howManyUserInChannel; i++) {
            User mockUser = new User();
            entityManager.persist(mockUser);  // This saves the User and generates the ID
            mockUser.mockUser(password);
            mockUserList.add(mockUser);
            userRepository.save(mockUser);
        }
        // Mock Channel 생성, Mock User 등록, Mock Content 등록
        for (int i = 0; i < howManyChannel; i++) {
            Channel channel = Channel.builder()
                    .channelName(mockUserList.get(0).getUsername() + "의 Mock Channel")
                    .channelCreateUser(mockUserList.get(0))
                    .channelPassword(password)
                    .channelDescription("Mock Channel Description")
                    .isPrivate(isPrivate)
                    .build();
            channelRepository.save(channel);

            // 생성한 채널에 유저를 등록하고 메세지 등록
            for (User user : mockUserList) {
                // 첫번째 유저라면 관리자로 등록
                if (user.getId().equals(mockUserList.get(0).getId())) {
                    UserChannel userChannel = UserChannel.builder()
                            .user(user)
                            .channel(channel)
                            .isAdmin(true)
                            .build();
                    userChannelRepository.save(userChannel);
                }

                UserChannel userChannel = UserChannel.builder()
                        .user(user)
                        .channel(channel)
                        .isAdmin(false)
                        .build();
                userChannelRepository.save(userChannel);

                // 유저의 메세지 생성
                for (int j = 0; j < howManyContentPerUser; j++) {
                    Message message = Message.builder()
                            .user(user)
                            .channel(channel)
                            .notReadCount(null)
                            .message("Mock Message")
                            .type(null)
                            .build();

                    messageRepository.save(message);
                }

            }

            dtoList.add(new CreateMockChannelResponseDto(channel));
        }

        return dtoList;
    }

}
