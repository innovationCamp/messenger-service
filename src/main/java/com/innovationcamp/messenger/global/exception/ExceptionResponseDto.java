package com.innovationcamp.messenger.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ExceptionResponseDto {
    private HttpStatus httpStatus;
    private String exceptionName;
    private String message;
}
