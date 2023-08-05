package com.innovationcamp.messenger.domain.testmessage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestMessageService {

    private final ObjectMapper objectMapper;
    private Map<String, TestMessageRoomDto> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<TestMessageRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public TestMessageRoomDto findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public TestMessageRoomDto createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        TestMessageRoomDto chatRoom = TestMessageRoomDto.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
