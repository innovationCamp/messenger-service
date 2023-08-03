package com.innovationcamp.messenger.domain.exception.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innovationcamp.messenger.domain.exception.dto.ExceptionResponseDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j(topic = "GlobalExceptionHandler")
public class GlobalExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponseDto> nullPointerExceptionHandler(IllegalArgumentException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponseDto> jsonProcessingExceptionHandler(JsonProcessingException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> validationExceptionHandler(ValidationException e) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
