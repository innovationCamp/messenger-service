package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.message.dto.MessageResponseDto;
import com.innovationcamp.messenger.domain.message.dto.TestMessageRoomUserDto;
import com.innovationcamp.messenger.domain.message.service.MessageService;
import com.innovationcamp.messenger.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/test")
public class ChannelPageController {
    @NonNull
    private final MessageService messageService;
    // 채팅 테스트를 프론트 없이는 힘들어서 구현
    // 프론트 분리되면 사라질 예정입니다.

    // 로그인 -> 채팅방 생성 -> 입장

    @Operation(summary = "로그인 페이지입니다.")
    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }

    @Operation(summary = "채팅 채널 리스트 페이지입니다.")
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/message/room";
    }

    @Operation(summary = "채팅 채널방 페이지입니다.")
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/message/roomdetail";
    }

    @Operation(summary = "채팅 내역 가져오기", description = """
            ChannelController의 @GetMapping("/{channelId}/content")와 같은 역할 입니다. 
            이후 file이 추가를 생각한다면 UserChannel로 구현 해야합니다.
            UserChannel에서 message를 가져오는 법을 몰라서 message로 구현했습니다.
            """)
    @GetMapping("/{roomId}/content")
    @ResponseBody
    public List<MessageResponseDto> getAllMessage(@PathVariable Long roomId){
        return messageService.getAllMessage(roomId);
    }

    @Operation(summary = "채팅 채널방 기본 구성", description = """
            채팅방 입장시 채팅방의 이름, 접속한 유저 이름, 최초입장 여부 확인 정보를 반환합니다.
            
            들어가는 시점에서 채팅방 이름 확인가능 + 로그인한 상태이기 때문에
            채팅방이름과 유저이름은 원래 프론트에서 처리 가능하다고 생각합니다.
            """)
    @GetMapping("/{roomId}")
    @ResponseBody
    public TestMessageRoomUserDto getRoomAndUserInfo(@RequestAttribute User user,
                                           @PathVariable Long roomId) {
        return messageService.getRoomAndUserInfo(roomId, user);
    }
}
