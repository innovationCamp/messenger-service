package com.innovationcamp.messenger.global.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j(topic = "GlobalExceptionHandler")
public class GlobalExceptionHandler {
    // user/singup에서 다음과 같이 입력하면( , 을 일부러 남김) 콘솔에서 "아직 정의하지 않은 예외 발생" 확인가능합니다
    /*
    {
      "email": "string",
    }
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> generalExceptionHandler(Exception e) {
        log.error("아직 정의하지 못한 예외 발생: ", e);
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    // General handler for exceptions to reduce code duplication
    private ResponseEntity<ExceptionResponseDto> createResponse(HttpStatus status, Exception e) {
        log.error("Exception handled: ", e);  // Logging the entire exception stack trace
        ExceptionResponseDto responseDto = new ExceptionResponseDto(status, e.getClass().getSimpleName(), e.getMessage());
        return ResponseEntity.status(status).body(responseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return createResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponseDto> nullPointerExceptionHandler(NullPointerException e) {
        return createResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ExceptionResponseDto> jsonProcessingExceptionHandler(JsonProcessingException e) {
        return createResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> validationExceptionHandler(ValidationException e) {
        return createResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> validationExceptionHandler(MethodArgumentNotValidException e) {
        String message = "Validation error";
        String detailedErrorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("Exception handled: ", e);  // Logging the entire exception stack trace
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(), message, detailedErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}