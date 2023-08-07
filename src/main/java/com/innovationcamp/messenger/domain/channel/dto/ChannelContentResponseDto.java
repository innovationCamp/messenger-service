package com.innovationcamp.messenger.domain.channel.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ChannelContentResponseDto {
    private final Long id;
    private final String userEmail;
    private final String userName;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;


    public ChannelContentResponseDto(Long id,
                                     String userEmail,
                                    String userName,
                                     Long callOutContentId,
                                     LocalDateTime createdAt,
                                     Long notReadCount) {
        this.id = id;
        this.userEmail = userEmail;
        this.userName = userName;
        this.callOutContentId = callOutContentId;
        this.createdAt = createdAt;
        this.notReadCount = notReadCount;
    }

}
