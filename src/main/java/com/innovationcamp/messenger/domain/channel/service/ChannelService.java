package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.ChannelCreateDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;

import java.util.List;

public interface ChannelService {
    Channel createChannel(ChannelCreateDto channelCreateDto);
    Channel getChannel(Long id);
    List<Channel> getChannelsUserJoined(Long userId);
    Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto);
    void deleteChannel(Long id);
    List<ChannelContent> getChannelContents(Long channelId);
    void addUserToChannel(Long channelId, Long userId);
    void kickUserFromChannel(Long channelId, Long userId);
    // other methods...
}