package com.innovationcamp.messenger.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionResponseDto {

    private int statusCode;
    private String status;
    private String exceptionName;
    private String message;
    private String detailedMessage; // Optional detailed error messages
    private LocalDateTime timestamp;

    // Constructor to make it easier to pass in HttpStatus without detailed message
    public ExceptionResponseDto(HttpStatus httpStatus, String exceptionName, String message) {
        this(httpStatus, exceptionName, message, null);
    }

    // Constructor with detailed message
    public ExceptionResponseDto(HttpStatus httpStatus, String exceptionName, String message, String detailedMessage) {
        this.statusCode = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.exceptionName = exceptionName;
        this.message = message;
        this.detailedMessage = detailedMessage;
        this.timestamp = LocalDateTime.now();
    }
}