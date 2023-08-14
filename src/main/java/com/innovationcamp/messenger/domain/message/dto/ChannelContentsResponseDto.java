package com.innovationcamp.messenger.domain.message.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChannelContentsResponseDto {
    private final Long id;
    private final String userName;
    private final Long channelId;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;


    public ChannelContentsResponseDto(Long id,
                                      String userName,
                                      Long channelId,
                                      Long callOutContentId,
                                      LocalDateTime createdAt,
                                      Long notReadCount) {
        this.id = id;
        this.userName = userName;
        this.channelId = channelId;
        this.callOutContentId = callOutContentId;
        this.createdAt = createdAt;
        this.notReadCount = notReadCount;
    }

}
