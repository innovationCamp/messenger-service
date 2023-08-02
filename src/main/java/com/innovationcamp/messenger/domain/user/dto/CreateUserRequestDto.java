package com.innovationcamp.messenger.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    @Email
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,10}$", message = "영문, 숫자, 한글로 이루어진 3~10자 이내로 작성해주세요")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[`~!@#$%^&*()])[a-zA-Z0-9`~!@#$%^&*()]{8,15}$", message = "영문자와 숫자, 특수문자를 포함한 8~15자 이내로 작성해주세요")
    private String password;
}
