package com.innovationcamp.messenger.domain.testmessage.dto;

import com.innovationcamp.messenger.domain.testmessage.service.TestMessageService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class TestMessageRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public TestMessageRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, TestMessageDto testMessageDto, TestMessageService testMessageService) {
        if (testMessageDto.getType().equals(TestMessageDto.MessageType.ENTER)) {
            sessions.add(session);
            testMessageDto.setMessage(testMessageDto.getSender() + "님이 입장했습니다.");
        }
        sendMessage(testMessageDto, testMessageService);
    }

    public <T> void sendMessage(T message, TestMessageService testMessageService) {
        sessions.parallelStream().forEach(session -> testMessageService.sendMessage(session, message));
    }
}
