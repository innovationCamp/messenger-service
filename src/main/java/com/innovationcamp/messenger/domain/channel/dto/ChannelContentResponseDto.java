package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.ChannelContentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelContentResponseDto {
    private Long id;
    private Long userId;
    private Long channelId;
    private Long callOutContentId;
    private LocalDateTime createdAt;
    private Long notReadCount;
    private ChannelContentType contentType;

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
