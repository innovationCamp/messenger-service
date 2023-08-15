package com.innovationcamp.messenger.domain.user.config;

import com.innovationcamp.messenger.domain.user.interceptor.JwtInterceptor;
import com.innovationcamp.messenger.domain.user.jwt.JwtUtil;
import com.innovationcamp.messenger.domain.user.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class JwtInterceptorConfig implements WebMvcConfigurer {
    @NonNull
    private final JwtUtil jwtUtil;
    @NonNull
    private final UserRepository userRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtUtil, userRepository))
                .addPathPatterns("/**")
                //order 기본값이 0 이라서 지정 안 해도 될 것 같습니다.
                .excludePathPatterns(
                        "/",
                        // 가끔씩(10분에 한번 정도?) GET : / 요청이 발생하여 토큰 없음 오류 발생함.
                        // 정확한 이유 모름, 웹소켓이 자동으로 끊어지는 것인지 확인 필요
                        "/api/user/signup",
                        "/api/user/login",
                        "/api/test/login",
                        "/api/mock/**",
                        "/error",
                        "/swagger-ui/**", "/webjars/**", "/v3/api-docs/**", "/swagger-resources/**", "/v3/api-docs.yaml");

    }
}
