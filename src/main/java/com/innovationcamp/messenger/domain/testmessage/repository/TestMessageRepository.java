//package com.innovationcamp.messenger.domain.testmessage.repository;
//
//import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
//import jakarta.annotation.PostConstruct;
//
//import java.util.*;
//
//public class TestMessageRepository {
//    private Map<String, TestMessageRoomDto> testMessageRoomDtoMap;
//
//    @PostConstruct
//    private void init() {
//        testMessageRoomDtoMap = new LinkedHashMap<>();
//    }
//
//    public List<TestMessageRoomDto> findAllRoom() {
//        // 채팅방 생성순서 최근 순으로 반환
//        List testMessageRoomDtos = new ArrayList<>(testMessageRoomDtoMap.values());
//        Collections.reverse(testMessageRoomDtos);
//        return testMessageRoomDtos;
//    }
//
//    public TestMessageRoomDto findRoomById(String id) {
//        return testMessageRoomDtoMap.get(id);
//    }
//
//    public TestMessageRoomDto createTestMessageRoomDto(String name) {
//        TestMessageRoomDto testMessageRoomDto = TestMessageRoomDto.create(name);
//        testMessageRoomDtoMap.put(testMessageRoomDto.getRoomId(), testMessageRoomDto);
//        return testMessageRoomDto;
//    }
//}
