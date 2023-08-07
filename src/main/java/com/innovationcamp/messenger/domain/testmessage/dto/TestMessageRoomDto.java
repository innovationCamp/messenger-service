package com.innovationcamp.messenger.domain.testmessage.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TestMessageRoomDto {
    private String roomId;
    private String name;

    public static TestMessageRoomDto create(String name){
        TestMessageRoomDto testMessageRoomDto = new TestMessageRoomDto();
        testMessageRoomDto.roomId = UUID.randomUUID().toString();
        testMessageRoomDto.name = name;
        return testMessageRoomDto;
    }
}
