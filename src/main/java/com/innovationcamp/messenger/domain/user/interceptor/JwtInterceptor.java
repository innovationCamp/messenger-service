package com.innovationcamp.messenger.domain.user.interceptor;

import com.innovationcamp.messenger.domain.user.jwt.JwtUtil;
import com.innovationcamp.messenger.domain.user.jwt.UserModel;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenValue = jwtUtil.getTokenFromCookie(request);
        log.info("작동중 토큰 : " + tokenValue);
        if (StringUtils.hasText(tokenValue)){
            tokenValue = jwtUtil.substringToken(tokenValue);
            if (!jwtUtil.validateToken(tokenValue)){
                throw new IllegalArgumentException("검증되지 않은 토큰");
            }
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
            UserModel userModel = new UserModel(info.getSubject(), info.get(JwtUtil.NICKNAME_KEY, String.class));
            request.setAttribute("userModel", userModel);
            return true;
        }
        return false;
    }
}
