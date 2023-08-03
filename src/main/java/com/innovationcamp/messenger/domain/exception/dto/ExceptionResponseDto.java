package com.innovationcamp.messenger.domain.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ExceptionResponseDto {
    private HttpStatus httpStatus;
    private String message;
}
