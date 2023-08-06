package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.ChannelCreateDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final UserChannelRepository userChannelRepository;
    private final ChannelContentRepository channelContentRepository;

    @Override
    public Channel createChannel(ChannelCreateDto channelCreateDto) {
        // Create new Channel entity based on the data in the NewChannelRequestDto
        Channel channel = Channel.builder()
                .channelName(channelCreateDto.getChannelName())
                .channelDescription(channelCreateDto.getChannelDescription())
                .build();

        // Save new Channel entity in the database
        return channelRepository.save(channel);
    }
    @Override
    public Channel getChannel(Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Channel not found"));
    }
    @Override
    public List<Channel> getChannelsUserJoined(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Fetch UserChannels where user is present
        List<UserChannel> userChannels = userChannelRepository.findByUser(user);

        // Extract Channels from UserChannels
        List<Channel> channels = userChannels.stream()
                .map(UserChannel::getChannel)
                .collect(Collectors.toList());

        return channels;
    }

    @Transactional
    @Override
    public Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto) {
        Channel channel = getChannel(id);
        // Update properties of the channel based on the updateChannelRequestDto...
        return channelRepository.save(channel);
    }

    @Transactional
    @Override
    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }

    @Override
    public List<ChannelContent> getChannelContents(Long channelId) {
        Channel channel = getChannel(channelId);
        return channelContentRepository.findByChannel(channel);
    }

    @Transactional
    @Override
    public void addUserToChannel(Long channelId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);

        UserChannel userChannel = UserChannel.builder()
                .user(user)
                .channel(channel)
                .build();

        userChannelRepository.save(userChannel);
    }

    @Transactional
    @Override
    public void kickUserFromChannel(Long channelId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Channel channel = getChannel(channelId);

        userChannelRepository.deleteByUserAndChannel(user, channel);
    }

    // Other methods...
}
