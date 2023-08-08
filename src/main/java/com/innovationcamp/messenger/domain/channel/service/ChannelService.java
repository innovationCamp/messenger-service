package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.ChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.CreateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UpdateChannelRequestDto;
import com.innovationcamp.messenger.domain.channel.dto.UserChannelResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface ChannelService {
    Channel createChannel(CreateChannelRequestDto createChannelRequestDto,@ModelAttribute UserModel user);
    Channel getChannel(Long id);
    List<UserChannelResponseDto> getAllChannelUserIn(Long userId);
    Channel updateChannel(Long id, UpdateChannelRequestDto updateChannelRequestDto);
    void deleteChannel(Long id);
    List<ChannelContentResponseDto> getChannelContents(Long channelId);
    void addUserToChannel(Long channelId, Long userId);
    void kickUserFromChannel(Long channelId, Long userId);

}