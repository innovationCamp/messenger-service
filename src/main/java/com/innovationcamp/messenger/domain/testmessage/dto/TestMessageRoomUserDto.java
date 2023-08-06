package com.innovationcamp.messenger.domain.testmessage.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import lombok.Getter;

@Getter
public class TestMessageRoomUserDto {
    private String roomId;
    private String name;
    private String sender;


    public TestMessageRoomUserDto(Channel channel, String username){
        this.roomId = channel.getId().toString();
        this.name = channel.getChannelName();
        this.sender = username;
    }
}
