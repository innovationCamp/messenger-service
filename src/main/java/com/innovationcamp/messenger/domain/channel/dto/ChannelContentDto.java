package com.innovationcamp.messenger.domain.channel.dto;

import com.innovationcamp.messenger.domain.channel.entity.ChannelContentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChannelContentDto {
    private Long id;
    private ChannelContentType contentType;
    private LocalDateTime createdAt;
    private Long channelId;
    private Long userId;
    private String userName;



}
