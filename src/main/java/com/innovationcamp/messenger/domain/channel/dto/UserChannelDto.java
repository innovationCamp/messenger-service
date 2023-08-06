package com.innovationcamp.messenger.domain.channel.dto;

import java.time.LocalDateTime;

public class UserChannelDto {
    private Long channelId;
    private String channelName;
    private String channelDescription;

    //read_timestamp
    private LocalDateTime readTimestamp;

    // is_admin
    private boolean isAdmin;


}
