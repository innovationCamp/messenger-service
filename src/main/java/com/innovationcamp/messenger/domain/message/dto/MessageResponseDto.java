package com.innovationcamp.messenger.domain.message.dto;

import com.innovationcamp.messenger.domain.message.entity.Message;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto extends ChannelContentsResponseDto {
    private String text;
    private MessageRequestDto.MessageType type;

    @Builder
    public MessageResponseDto(Long id,
                              String username,
                              Long channelId,
                              Long callOutId,
                              LocalDateTime createdAt,
                              Long notReadCount,
                              String text,
                              MessageRequestDto.MessageType type) {
        super(id, username, channelId, callOutId, createdAt, notReadCount);
        this.text = text;
        this.type = type;
    }
}
