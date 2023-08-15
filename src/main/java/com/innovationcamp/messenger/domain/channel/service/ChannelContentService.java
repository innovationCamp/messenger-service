package com.innovationcamp.messenger.domain.channel.service;

import com.innovationcamp.messenger.domain.channel.dto.GetChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.entity.UserChannel;
import com.innovationcamp.messenger.domain.channel.repository.ChannelContentRepository;
import com.innovationcamp.messenger.domain.channel.repository.ChannelRepository;
import com.innovationcamp.messenger.domain.channel.repository.UserChannelRepository;
import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelContentService {
    @NonNull
    private final ChannelRepository channelRepo;
    @NonNull
    private final ChannelContentRepository channelContentRepo;
    @NonNull
    private final UserChannelRepository userChannelRepo;


    public List<GetChannelContentResponseDto> getChannelContents(Long channelId, User user) {
        Channel channel = channelRepo.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Channel not found"));

        checkUserInChannel(channel, user);

        List<ChannelContent> channelContents = channelContentRepo.findByChannel(channel);

        List<GetChannelContentResponseDto> dtoList = channelContents.stream()
                .map(channelContent -> {
                    User channelContentUser = channelContent.getUser();
                    ChannelContent calloutContent = channelContent.getCalloutContent();
                    // NullPointException 방지
                    Long calloutContentId = calloutContent != null ? calloutContent.getId() : null;
                    return new GetChannelContentResponseDto(
                            channelContent.getId(),
                            channelContentUser.getUsername(),
                            channelContentUser.getEmail(),
                            calloutContentId,
                            channelContent.getCreatedAt(),
                            channelContent.getNotReadCount());
                })
                .collect(Collectors.toList());

        return dtoList;
    }

    // 사용자가 채널에 속해 있는지 확인
    private void checkUserInChannel(Channel channel, User user) {
        Optional<UserChannel> userChannel = userChannelRepo.findByUserAndChannel(user, channel);

        if (userChannel.isEmpty()) {
            throw new IllegalArgumentException("채널 내용을 조회하려면 해당 채널에 속해 있어야 합니다.");
        }
    }
}
