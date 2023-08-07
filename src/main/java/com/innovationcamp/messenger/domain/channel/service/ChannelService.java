package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.ChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.CreateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UserChannelResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;

import java.util.List;

public interface ChannelService {
    Channel createChannel(CreateChannelRequestDto createChannelRequestDto);
    Channel getChannel(Long id);
    List<UserChannelResponseDto> getChannelsUserJoined(Long userId);
    Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto);
    void deleteChannel(Long id);
    List<ChannelContentResponseDto> getChannelContents(Long channelId);
    void addUserToChannel(Long channelId, Long userId);
    void kickUserFromChannel(Long channelId, Long userId);
    // other methods...
}