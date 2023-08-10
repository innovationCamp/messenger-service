package com.innovationcamp.messenger.domain.user.interceptor;

import com.innovationcamp.messenger.domain.user.entity.User;
import com.innovationcamp.messenger.domain.user.jwt.JwtUtil;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtInterceptor(JwtUtil jwtUtil, UserRepository userRepository){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenValue = jwtUtil.getTokenFromCookie(request);
        if (tokenValue == null) {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
        log.info("작동중 토큰 : " + tokenValue);
        if (StringUtils.hasText(tokenValue)){
            tokenValue = jwtUtil.substringToken(tokenValue);
            if (!jwtUtil.validateToken(tokenValue)){
                throw new IllegalArgumentException("검증되지 않은 토큰");
            }
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
            User user = userRepository.findById(Long.valueOf(info.getSubject())).orElseThrow(()->new IllegalArgumentException("없는 유저입니다."));
            request.setAttribute("user", user);
            return true;
        }
        return false;
    }
}
