package com.innovationcamp.messenger.domain.message.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import lombok.Getter;

@Getter
public class TestMessageRoomUserDto {
    private String roomId;
    private String name;
    private String sender;
    private boolean firstEntry;


    public TestMessageRoomUserDto(Channel channel, String username, boolean firstEntry){
        this.roomId = channel.getId().toString();
        this.name = channel.getChannelName();
        this.sender = username;
        this.firstEntry = firstEntry;
    }
}
