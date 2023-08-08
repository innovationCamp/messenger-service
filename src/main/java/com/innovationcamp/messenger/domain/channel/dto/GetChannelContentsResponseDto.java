package com.innovationcamp.messenger.domain.channel.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "ChannelContent 조회 response Dto")
public class GetChannelContentsResponseDto {
    private final Long id;
    private final String userEmail;
    private final String userName;
    private final Long callOutContentId;
    private final LocalDateTime createdAt;
    private final Long notReadCount;


    public GetChannelContentsResponseDto(Long id,
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
