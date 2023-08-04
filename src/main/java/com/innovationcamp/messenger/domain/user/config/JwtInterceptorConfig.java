package com.innovationcamp.messenger.domain.user.config;

import com.innovationcamp.messenger.domain.user.interceptor.JwtInterceptor;
import com.innovationcamp.messenger.domain.user.jwt.JwtUtil;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtUtil))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/signup","/api/user/login",
                        "/swagger-ui/**", "/webjars/**",
                        "/v3/api-docs/**", "/swagger-resources/**");
    }
}
