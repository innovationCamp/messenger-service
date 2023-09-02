package com.innovationcamp.messenger.domain.channel.controller;

import com.innovationcamp.messenger.domain.channel.dto.GetChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.dto.GetMongoChannelContentResponseDto;
import com.innovationcamp.messenger.domain.channel.service.ChannelContentService;
import com.innovationcamp.messenger.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/channel-content")
@Tag(name = "ChannelContentController", description = "@RequestAttribute User에서 현재 사용자 정보를 얻습니다.")
public class ChannelContentController {
    @NonNull    
    private final ChannelContentService channelContentService;
//    @Operation(summary = "GET ALL ChannelContent", description = """
//            ChannelContent를 가져오려면 사용자가 해당 채널에 이미 속해있어야 합니다.
//            """)
//    @GetMapping("/{channelId}")
//    public ResponseEntity<List<GetChannelContentResponseDto>> getChannelContents(@PathVariable Long channelId, @RequestAttribute User user) {
//        List<GetChannelContentResponseDto> dtolist = channelContentService.getChannelContents(channelId, user);
//        return ResponseEntity.ok(dtolist);
//    }

    @Operation(summary = "GET ALL ChannelContent", description = """
            ChannelContent를 가져오려면 사용자가 해당 채널에 이미 속해있어야 합니다.
            """)
    @GetMapping("/{channelId}")
    public ResponseEntity<List<GetMongoChannelContentResponseDto>> getChannelContents(@PathVariable Long channelId, @RequestAttribute User user) {
        List<GetMongoChannelContentResponseDto> dtolist = channelContentService.getChannelContents(channelId, user);
        return ResponseEntity.ok(dtolist);
    }
}
