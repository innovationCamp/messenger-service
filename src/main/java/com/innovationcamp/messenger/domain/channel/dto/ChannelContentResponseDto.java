package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.ChannelContentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ChannelContentResponseDto {
    private final Long id;
    private final Long userId;
    private final Long channelId;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;
    private final ChannelContentType contentType;

    public ChannelContentResponseDto(Long id,
                                     Long userId,
                                     Long channelId,
                                     Long callOutContentId,
                                     LocalDateTime createdAt,
                                     Long notReadCount,
                                     ChannelContentType contentType) {
        this.id = id;
        this.userId = userId;
        this.channelId = channelId;
        this.callOutContentId = callOutContentId;
        this.createdAt = createdAt;
        this.notReadCount = notReadCount;
        this.contentType = contentType;
    }

}
