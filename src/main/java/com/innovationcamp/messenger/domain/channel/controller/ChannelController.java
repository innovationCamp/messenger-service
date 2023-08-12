package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.channel.dto.*;
import com.innovationcamp.messenger.domain.channel.service.ChannelServiceImpl;
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
    private final ChannelServiceImpl channelService;

    @Operation(summary = "채널 생성", description = """
            채널 생성 후 사용자를 관리자 권한으로 채널에 등록합니다.


            생성 성공시 채널 정보와 생성한 사용자의 username을 리턴합니다.""")
    @PostMapping
    public ResponseEntity<CreateChannelResponseDto> createChannel(@RequestAttribute User user, @RequestBody CreateChannelRequestDto createDto) {
        CreateChannelResponseDto dto = channelService.createChannel(user, createDto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "채널 검색", description = """
            채널 이름을 통해 채널을 검색합니다. 채널 이름은 중복될 수 있습니다.
            """)
    @GetMapping("/search")
    public ResponseEntity<List<GetChannelResponseDto>> searchChannel(@RequestParam String keyword) {
        if (keyword.isEmpty()) throw new IllegalArgumentException("검색할 키워드를 입력하세요.");
        List<GetChannelResponseDto> dtoList = channelService.searchChannel(keyword);
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널의 정보 조회", description = """
            특정 채널의 정보( content, password 제외 )를 얻을 수 있습니다. 현재 사용자 정보는 사용하지 않습니다.

            getChannel(@PathVariable Long channelId)""")
    @GetMapping("/{channelId}")
    public ResponseEntity<GetChannelResponseDto> getChannel(@PathVariable Long channelId) {
        GetChannelResponseDto dto = channelService.getChannel(channelId);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "사용자가 참여중인 채널 목록(UserChannel)을 조회합니다.", description = "getAllChannelUserIn(@RequestAttribute User user)")
    @GetMapping
    public ResponseEntity<List<GetAllChannelUserInResponseDto>> getAllChannelUserIn(@RequestAttribute User user) {
        List<GetAllChannelUserInResponseDto> responseDtoList = channelService.getAllChannelUserIn(user);
        return ResponseEntity.ok(responseDtoList);
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

    @Operation(summary = "사용자가 속한 특정 채널의 content 전체 조회", description = "getChannelContents(@PathVariable Long, @RequestAttribute User)")
    @GetMapping("/{channelId}/content")
    public ResponseEntity<List<GetChannelContentsResponseDto>> getChannelContents(@PathVariable Long channelId, @RequestAttribute User user) {
        List<GetChannelContentsResponseDto> responseDtoList = channelService.getChannelContents(channelId, user);
        return ResponseEntity.ok(responseDtoList);
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
