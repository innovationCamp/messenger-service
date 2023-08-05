package com.innovationcamp.messenger.domain.testmessage.controller;

import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
import com.innovationcamp.messenger.domain.testmessage.service.TestMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test/message")
public class TestMessageController {

    private final TestMessageService testMessageService;

    @PostMapping
    public TestMessageRoomDto createRoom(@RequestParam String name) {
        return testMessageService.createRoom(name);
    }

    @GetMapping
    public List<TestMessageRoomDto> findAllRoom() {
        return testMessageService.findAllRoom();
    }
}