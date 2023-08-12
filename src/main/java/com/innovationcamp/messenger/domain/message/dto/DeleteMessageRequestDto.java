package com.innovationcamp.messenger.domain.message.dto;

import lombok.Getter;

@Getter
public class DeleteMessageRequestDto {
    private Long channelId; // 방번호
    private Long channelContentId; // 삭제할 메세지
    private String sender; // 보낸사람
}
