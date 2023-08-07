package com.innovationcamp.messenger.domain.user.controller;

import com.innovationcamp.messenger.domain.channel.dto.UserChannelResponseDto;
import com.innovationcamp.messenger.domain.channel.entity.Channel;
import com.innovationcamp.messenger.domain.channel.service.ChannelService;
import com.innovationcamp.messenger.domain.user.dto.UserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.LoginUserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.UserResponseDto;
import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import com.innovationcamp.messenger.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @NonNull
    private final UserService userService;
    @NonNull
    private final ChannelService channelService;

    // Existing methods...

    @Operation(summary = "유저가 참여중인 채널 목록을 조회합니다.")
    @GetMapping("/{userId}/channel")
    public ResponseEntity<List<UserChannelResponseDto>> getChannelsUserJoined(@PathVariable Long userId){
        List<UserChannelResponseDto> responseDtoList = channelService.getChannelsUserJoined(userId);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping("/signup")
    public UserResponseDto signUpUser(@RequestBody UserRequestDto requestDto){
        return userService.signUpUser(requestDto);
    }
    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody LoginUserRequestDto requestDto, HttpServletResponse res){
        return userService.loginUser(requestDto, res);
    }

    @PutMapping
    public UserResponseDto updateUser(@RequestBody UserRequestDto requestDto,
                                      @ModelAttribute UserModel userModel,
                                      HttpServletResponse res){
        return userService.updateUser(requestDto, userModel, res);
    }

    @DeleteMapping
    public String deleteUser(@ModelAttribute UserModel userModel){
        return userService.deleteUser(userModel);
    }

    @GetMapping
    public UserResponseDto getUser(@ModelAttribute UserModel userModel){
        return userService.getUser(userModel);
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse res){
        return userService.logoutUser(res);
    }

}
