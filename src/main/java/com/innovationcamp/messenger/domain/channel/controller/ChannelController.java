package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.channel.dto.*;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.entity.ChannelContent;
import com.innovationcamp.messenger.domain.channel.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/channel")
@Tag(name = "Channel", description = "Channel, UserChannel, ChannelContent")
public class ChannelController {
    @NonNull
    private final ChannelService channelService;

    @Operation(summary = "채널 생성")
    @PostMapping
    public ResponseEntity<CreateChannelResponseDto> createChannel(@RequestBody CreateChannelRequestDto createDto) {
        Channel createdChannel = channelService.createChannel(createDto);
        CreateChannelResponseDto responseDto = createdChannel.toResponseDto();
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널 조회", description = "채널 id는 /api/user/{userId}/channel으로 조회 가능")
    @GetMapping("/{id}")
    public ResponseEntity<ChannelSingleResponseDto> getChannel(@PathVariable Long id){
        Channel channel = channelService.getChannel(id);
        ChannelSingleResponseDto responseDto = channel.toInfoResponseDto();
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널 수정")
    @PutMapping("/{id}")
    public ResponseEntity<UpdateChannelResponseDto> updateChannel(@PathVariable Long id, @RequestBody UpdateChannelRequestDto updateChannelRequestDto){
        Channel updatedChannel = channelService.updateChannel(id, updateChannelRequestDto);
        UpdateChannelResponseDto responseDto = updatedChannel.toUpdateResponseDto();
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "채널 id를 사용하여 특정 채널 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id){
        channelService.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 채널의 채널 내용 전체 조회")
    @GetMapping("/{id}/content")
    public ResponseEntity<List<ChannelContentResponseDto>> getChannelContents(@PathVariable Long id){
        List<ChannelContentResponseDto> responseDtoList = channelService.getChannelContents(id);
        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "특정 채널에 유저 추가")
    @PostMapping("/{channelId}/user/{userId}")
    public ResponseEntity<Void> addUserToChannel(@PathVariable Long channelId, @PathVariable Long userId){
        channelService.addUserToChannel(channelId, userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "특정 채널에서 유저 추방")
    @DeleteMapping("/{channelId}/user/{userId}")
    public ResponseEntity<Void> kickUserFromChannel(@PathVariable Long channelId, @PathVariable Long userId){
        channelService.kickUserFromChannel(channelId, userId);
        return ResponseEntity.noContent().build();
    }


}
