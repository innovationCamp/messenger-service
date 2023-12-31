package com.innovationcamp.messenger.domain.user.service;

import com.innovationcamp.messenger.domain.user.config.PasswordEncoder;
import com.innovationcamp.messenger.domain.user.dto.UserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.LoginUserRequestDto;
import com.innovationcamp.messenger.domain.user.dto.UserResponseDto;
import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.jwt.JwtUtil;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final PasswordEncoder passwordEncoder;
    @NonNull
    private final JwtUtil jwtUtil;

    public UserResponseDto signUpUser(UserRequestDto requestDto) {
        checkUniqueEmail(requestDto.getEmail());

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .password(password)
                .build();

        userRepository.save(user);

        return new UserResponseDto(user);
    }

    public UserResponseDto loginUser(LoginUserRequestDto requestDto, HttpServletResponse res) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() ->
                new IllegalArgumentException("없는 이메일 입니다.")
        );

        if(!passwordEncoder.checkPassword(requestDto.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        String accessToken = jwtUtil.createToken(user);
        jwtUtil.addJwtToCookie(accessToken, res);

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(UserRequestDto requestDto, User user, HttpServletResponse res) {

        if (!requestDto.getEmail().equals(user.getEmail()))
            checkUniqueEmail(requestDto.getEmail());

        String password = passwordEncoder.encode(requestDto.getPassword());
        user.update(requestDto.getEmail(), requestDto.getUsername(), password);
        jwtUtil.deleteJwtCookie(res);
        return new UserResponseDto(user);
    }

    public String deleteUser(User user) {
        userRepository.delete(user);
        return "삭제완료";
    }

    public String logoutUser(HttpServletResponse res) {
        jwtUtil.deleteJwtCookie(res);
        return "로그아웃 성공";
    }

    private void checkUniqueEmail(String email) {
        if(userRepository.existsByEmail(email))
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
    }
}
