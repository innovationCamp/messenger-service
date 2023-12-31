package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.channel.dto.*;
import com.innovationcamp.messenger.domain.channel.service.ChannelService;
import com.innovationcamp.messenger.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO: 채널 검색, notReadCount 로직,  채널 탈퇴
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/channel")
@Tag(name = "ChannelController", description = "@RequestAttribute User에서 현재 사용자 정보를 얻습니다.")
public class ChannelController {
    @NonNull
    private final ChannelService channelService;

    @Operation(summary = "사용자가 관리자인 채널 생성", description = """
            채널 생성 후 사용자를 관리자 권한으로 채널에 등록합니다.

            생성 성공시 채널 정보와 생성한 사용자의 username을 리턴합니다.""")
    @PostMapping
    public ResponseEntity<CreateChannelResponseDto> createChannel(@RequestAttribute User user, @RequestBody CreateChannelRequestDto createDto) {
        CreateChannelResponseDto dto = channelService.createChannel(user, createDto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "키워드로 채널 검색", description = """
            채널 이름에 대소문자 구분 없이 키워드가 포함되어 있는 채널들을 검색합니다. (채널 이름은 중복될 수 있습니다.)
            """)
    @GetMapping("/search")
    public ResponseEntity<List<GetChannelResponseDto>> searchChannel(@RequestParam String keyword) {
        if (keyword.isEmpty()) throw new IllegalArgumentException("검색할 키워드를 입력하세요.");
        List<GetChannelResponseDto> dtoList = channelService.searchChannel(keyword);
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "사용자가 속한 채널 중 채널 id를 사용하여 단일 조회")
    @GetMapping("/{channelId}")
    public ResponseEntity<GetChannelResponseDto> getChannel(@PathVariable Long channelId, @RequestAttribute User user) {
        GetChannelResponseDto dto = channelService.getChannel(channelId, user);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "사용자가 속한 채널 목록(UserChannel)을 조회합니다.", description = "getAllChannelUserIn(@RequestAttribute User user)")
    @GetMapping
    public ResponseEntity<List<GetAllChannelUserInResponseDto>> getAllChannelUserIn(@RequestAttribute User user) {
        List<GetAllChannelUserInResponseDto> responseDtoList = channelService.getAllChannelUserIn(user);
        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널에 가입", description = """
            사용자가 해당 채널에 가입합니다. 가입 성공 시 사용자는 해당 채널에 속하게 됩니다.
            
            관리자 권한으로는 가입할 수 없습니다. 단, 자신이 생성한 채널에는 이미 관리자로 가입되어 있습니다.
               
            아직 채널에 관리자를 추가하는 기능은 없고 채널 생성한 사람만 관리자입니다.
            
            비밀 채널인 경우 비밀번호를 검사하고 그렇지 않은 경우 비밀번호와 상관 없이 가입됩니다.
            """)
    @PostMapping("/{channelId}/signup")
    public ResponseEntity<SignUpChannelResponseDto> signUpChannel(@PathVariable Long channelId,
                                                                 @RequestParam(required = false) String channelPassword,
                                                                 @RequestAttribute User user) {
        SignUpChannelResponseDto dto = channelService.signUpChannel(channelId, channelPassword, user);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널 수정", description = """
            채널 관리자는 채널 이름과 채널 설명을 수정할 수 있습니다. 채널 비밀번호 수정 기능은 없습니다.

            updateChannel(@PathVariable Long, @RequestBody UpdateChannelRequestDto, @RequestAttribute User)""")
    @PutMapping("/{channelId}")
    public ResponseEntity<UpdateChannelResponseDto> updateChannel(@PathVariable Long channelId
            , @RequestBody UpdateChannelRequestDto updateChannelRequestDto, @RequestAttribute User user) {
        UpdateChannelResponseDto dto = channelService.updateChannel(channelId, updateChannelRequestDto, user);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널 삭제", description = """
            채널 관리자가 채널에 등록된 사용자들을 모두 추방하고 채널을 삭제합니다.

            deleteChannel(@PathVariable Long, @RequestAttribute User)""")
    @DeleteMapping("/{channelId}")
    public ResponseEntity<String> deleteChannel(@PathVariable Long channelId, @RequestAttribute User user) {
        channelService.deleteChannel(channelId, user);
        return ResponseEntity.ok("채널 사용자들을 모두 추방하고 채널을 삭제했습니다.");
    }



    @Operation(summary = "사용자가 속한 특정 채널에 다른 유저 추가", description = "추가는 관리자가 아니어도 할 수 있습니다.")
    @PostMapping("/{channelId}/user/{otherUserId}")
    public ResponseEntity<Void> addUserToChannel(@PathVariable Long channelId, @PathVariable Long otherUserId, @RequestAttribute User user) {
        channelService.addUserToChannel(channelId, otherUserId, user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "사용자가 속한 특정 채널에서 다른 유저 추방", description = "추방은 관리자만 할 수 있습니다.")
    @DeleteMapping("/{channelId}/user/{otherUserId}")
    public ResponseEntity<Void> kickUserFromChannel(@PathVariable Long channelId, @PathVariable Long otherUserId, @RequestAttribute User user) {
        channelService.kickUserFromChannel(channelId, otherUserId, user);
        return ResponseEntity.noContent().build();
    }

}
