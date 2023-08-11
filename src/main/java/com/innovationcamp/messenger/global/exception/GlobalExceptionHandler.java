package com.innovationcamp.messenger.global.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innovationcamp.messenger.domain.user.entity.User;
import jakarta.validation.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j(topic = "GlobalExceptionHandler")
public class GlobalExceptionHandler {

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
        ExceptionResponseDto responseDto = new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(), message, detailedErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}