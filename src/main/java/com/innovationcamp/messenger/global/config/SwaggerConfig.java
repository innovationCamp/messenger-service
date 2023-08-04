package com.innovationcamp.messenger.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().
                info(new Info()
                        .title("회비관리 메신저 API")
                        .version("0.1")
                        .description("SwaggerConfig로 생성됨"))
                        .components(new Components()
                                .addSecuritySchemes("JWT",
                                        new SecurityScheme()
                                                .name("JWT")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                                .description("Bearer와 공백(또는%20)을 제외한 JWT값을 넣어주세요")))
                        .addSecurityItem(
                                new SecurityRequirement().addList("JWT", Arrays.asList("read", "write")));
    }
}
