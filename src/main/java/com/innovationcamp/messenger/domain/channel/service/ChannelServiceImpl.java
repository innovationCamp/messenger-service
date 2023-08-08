package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.config.ChannelPasswordEncoder;
import com.innovationcamp.messenger.domain.channel.dto.ChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.CreateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UserChannelResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    @NonNull
    private final ChannelRepository channelRepository;
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserChannelRepository userChannelRepository;
    @NonNull
    private final ChannelContentRepository channelContentRepository;
    @NonNull
    private final ChannelPasswordEncoder ChannelPasswordEncoder;

    //TODO: 채널 생성한 유저는 관리자 권한으로 해당 채널에 등록
    @Transactional
    @Override
    public Channel createChannel(CreateChannelRequestDto createChannelRequestDto, @ModelAttribute UserModel usermodel) {
        User user = userRepository.findById(usermodel.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        String channelName = usermodel.getUsername(); // 채널이름 기본값은 유저이름
        String password = null;
        String channelDescription = null;
        if(createChannelRequestDto.getChannelName() != null) {
            channelName = createChannelRequestDto.getChannelName();
        }
        if(createChannelRequestDto.getChannelPassword() != null) {
            password = ChannelPasswordEncoder.encode(createChannelRequestDto.getChannelPassword());
        }
        if(createChannelRequestDto.getChannelDescription() != null) {
            channelDescription = createChannelRequestDto.getChannelDescription();
        }

        Channel channel = Channel.builder()
                .channelName(channelName)
                .channelPassword(password)
                .channelDescription(channelDescription)
                .build();

        channelRepository.save(channel);

        UserChannel userChannel = UserChannel.builder()
                .user(user)
                .channel(channel)
                .isAdmin(true)
                .build();

        userChannelRepository.save(userChannel);

        return channel;
    }
    @Override
    public Channel getChannel(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel not found"));
    }
    @Override
    public List<UserChannelResponseDto> getAllChannelUserIn(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<UserChannel> userChannels = userChannelRepository.findByUser(user);

        return userChannels.stream()
                .map(userChannel -> {
                    Channel channel = userChannel.getChannel();
                    return new UserChannelResponseDto( channel.getId(),
                            userChannel.getReadTimestamp(), userChannel.isAdmin()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto) {
        Channel channel = getChannel(id);

        channel.update(updateChannelRequestDto);
        return channel;
    }

    @Override
    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<ChannelContentResponseDto> getChannelContents(Long channelId) {
        Channel channel = getChannel(channelId);

        List<ChannelContent> channelContents = channelContentRepository.findByChannel(channel);

        List<ChannelContentResponseDto> dtoList = channelContents.stream()
                .map(channelContent -> {
                    User user = channelContent.getUser();
                    ChannelContent calloutContent = channelContent.getCalloutContent();
                    // NullPointException 방지
                    Long calloutContentId = calloutContent != null ? calloutContent.getId() : null;
                    return new ChannelContentResponseDto(
                            channelContent.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            calloutContentId,
                            channelContent.getCreatedAt(),
                            channelContent.getNotReadCount());
                })
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void addUserToChannel(Long channelId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);

        checkChannelAdmin(channel, user);

        UserChannel userChannel = UserChannel.builder()
                .user(user)
                .channel(channel)
                .build();

        userChannelRepository.save(userChannel);
    }

    @Override
    public void kickUserFromChannel(Long channelId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);
        checkChannelAdmin(channel, user);
        userChannelRepository.deleteByUserAndChannel(user, channel);
    }

    // 수정, 삭제 시 권한 확인
    private void checkChannelAdmin(Channel channel, User user) {
        Optional<UserChannel> userChannel = userChannelRepository.findByUserAndChannel(user, channel);

        if(userChannel.isEmpty() || !userChannel.get().isAdmin()) {
            throw new IllegalArgumentException("You are not admin of this channel");
        }
    }

}
