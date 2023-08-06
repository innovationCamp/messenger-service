package com.innovationcamp.messenger.domain.testmessage.controller;

import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomDto;
import com.innovationcamp.messenger.domain.testmessage.dto.TestMessageRoomUserDto;
import com.innovationcamp.messenger.domain.testmessage.service.TestMessageService;
import com.innovationcamp.messenger.domain.user.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/test/message")
public class TestMessageRoomController {
    @NonNull
    private final TestMessageService testMessageService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/message/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<TestMessageRoomDto> findAllRoom(@ModelAttribute User user) {
        return testMessageService.findAllRoom(user);
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public TestMessageRoomDto createRoom(@ModelAttribute User user,
                                         @RequestParam String name) {
        return testMessageService.createTestMessageRoomDto(user, name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/message/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public TestMessageRoomUserDto roomInfo(@ModelAttribute User user,
                                           @PathVariable Long roomId) {
        return testMessageService.findRoomById(roomId, user.getUsername());
    }
}

