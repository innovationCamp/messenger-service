package com.innovationcamp.messenger.mock.controller;

import com.innovationcamp.messenger.mock.dto.CreateMockChannelResponseDto;
import com.innovationcamp.messenger.mock.service.MockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mock")
@Tag(name = "MockController", description = "테스트용 mock데이터 생성")
public class MockController {
    @NonNull
    private final MockService mockService;

    @Operation(summary = "mock데이터 생성", description = """
            원하는 만큼의 mock 채널을 생성, 각 채널에 mock user를 등록, 각 mock user가 mock content를 등록합니다.
            
            isPrivate으로 비밀 채널 여부를 설정할 수 있습니다. 기본값은 false입니다.
            
            채널 수, 각 채널 내 mock user 수, 각 mock user가 채널에 등록한 mock content 수를 입력받습니다.
            
            채널 수는 1 이상, 각 채널 내 mock user 수는 1 이상, 각 mock user는 0개 이상의 mock content를 등록해야 합니다.
            
            기본값은 각각 3, 3, 3 입니다.
            
            이곳에서 생성된 모든 비밀번호는 0000 입니다. (인코딩은 한 번만 이루어짐)
            """)
    @PostMapping("/channel")
    public ResponseEntity<List<CreateMockChannelResponseDto>> createMockChannel(
            @RequestParam(name = "isPrivate", defaultValue = "false") Boolean isPrivate,
            @RequestParam(name = "howManyChannel", defaultValue = "3") Long howManyChannel,
            @RequestParam(name = "howManyUserInChannel", defaultValue = "3") Long howManyUserInChannel,
            @RequestParam(name = "howManyContentPerUser", defaultValue = "3") Long howManyContentPerUser) {
        List<CreateMockChannelResponseDto> dtoList = mockService.createMockChannel(
                isPrivate, howManyChannel, howManyUserInChannel, howManyContentPerUser
        );
        return ResponseEntity.ok(dtoList);
    }
}
