package com.innovationcamp.messenger.domain.user.controller;

import com.innovationcamp.messenger.domain.user.dto.LoginUserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.UserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.UserResponseDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @NonNull
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signUpUser(@RequestBody @Valid UserRequestDto requestDto){
        return userService.signUpUser(requestDto);
    }
    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody LoginUserRequestDto requestDto, HttpServletResponse res){
        return userService.loginUser(requestDto, res);
    }

    @PutMapping
    public UserResponseDto updateUser(@RequestBody @Valid UserRequestDto requestDto,
                                      @ModelAttribute User user,
                                      HttpServletResponse res){
        return userService.updateUser(requestDto, user, res);
    }

    @DeleteMapping
    public String deleteUser(@ModelAttribute User user){
        return userService.deleteUser(user);
    }

    @GetMapping
    public UserResponseDto getUser(@ModelAttribute User user){
        return new UserResponseDto(user);
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletResponse res){
        return userService.logoutUser(res);
    }

}
