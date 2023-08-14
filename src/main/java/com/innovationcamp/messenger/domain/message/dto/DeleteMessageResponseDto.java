package com.innovationcamp.messenger.domain.message.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteMessageResponseDto {
    private final String type = "DELETE";
    private Long channelId; // 방번호
    private Long channelContentId; // 삭제할 메세지

    @Builder
    public DeleteMessageResponseDto(Long channelId, Long channelContentId){
        this.channelId = channelId;
        this.channelContentId = channelContentId;
    }
}
