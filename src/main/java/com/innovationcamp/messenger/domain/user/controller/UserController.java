package com.innovationcamp.messenger.domain.user.controller;

import com.innovationcamp.messenger.domain.user.dto.CreateUserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.LoginUserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.UserResponseDto;
import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import com.innovationcamp.messenger.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @NonNull
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signUpUser(@RequestBody CreateUserRequestDto requestDto){
        return userService.signUpUser(requestDto);
    }
    @PostMapping("/login")
    public UserResponseDto loginUser(@RequestBody LoginUserRequestDto requestDto, HttpServletResponse res){
        return userService.loginUser(requestDto, res);
    }

    @PutMapping
    public UserResponseDto updateUser(@RequestBody CreateUserRequestDto requestDto,
                                      @ModelAttribute UserModel userModel){
        System.out.println("userModel.getEmail() = " + userModel.getEmail());
        return userService.updateUser(requestDto, userModel);
    }

    @DeleteMapping
    public String deleteUser(@ModelAttribute UserModel userModel){
        return userService.deleteUser(userModel);
    }

    @GetMapping
    public UserResponseDto getUser(@ModelAttribute UserModel userModel){
        return userService.getUser(userModel);
    }

}
