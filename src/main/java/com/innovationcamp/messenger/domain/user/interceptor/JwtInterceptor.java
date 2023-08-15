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
        log.info(request.getMethod() + " : " + request.getServletPath());
        // preHandle request 메서드와 servletPath를 로그로 확인하여 트러블슈팅
        String tokenValue = jwtUtil.getTokenFromCookie(request);
        log.info("토큰 : " + tokenValue);
        if (StringUtils.hasText(tokenValue)){
            tokenValue = jwtUtil.substringToken(tokenValue);
            if (!jwtUtil.validateToken(tokenValue)){
                throw new IllegalArgumentException("토큰이 유효성 검사를 통과하지 못했습니다.");
            }
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
            User user = userRepository.findById(Long.valueOf(info.getSubject()))
                    .orElseThrow(()->new IllegalArgumentException("토큰의 정보와 일치하는 유저가 없습니다."));
            request.setAttribute("user", user);
            log.info("토큰을 통한 유저 정보 확인 완료");
            return true;
        } else throw new IllegalArgumentException("토큰이 없습니다.");
    }
}
