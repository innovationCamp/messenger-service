package com.innovationcamp.messenger.domain.testmessage.dto;

import com.innovationcamp.messenger.domain.channel.entity.Channel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TestMessageRoomDto {
    private String roomId;
    private String name;


    public TestMessageRoomDto(Channel channel){
        this.roomId = channel.getId().toString();
        this.name = channel.getChannelName();
    }
}
