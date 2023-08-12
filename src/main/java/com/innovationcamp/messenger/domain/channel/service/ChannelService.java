package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.*;
import com.innovationcamp.messenger.domain.user.entity.User;

import java.util.List;

public interface ChannelService {
    CreateChannelResponseDto createChannel(User user, CreateChannelRequestDto createChannelRequestDto);
    GetChannelResponseDto getChannel(Long id);
    List<GetAllChannelUserInResponseDto> getAllChannelUserIn(User user);
    UpdateChannelResponseDto updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto, User user);
    void deleteChannel(Long channelId, User user);
    List<GetChannelContentsResponseDto> getChannelContents(Long channelId, User user);
    void addUserToChannel(Long channelId, Long otherUserId, User user);
    void kickUserFromChannel(Long channelId, Long otherUserId, User user);
    ParticipantChannelDto participantByChannelId(Long channelId, User user);
}