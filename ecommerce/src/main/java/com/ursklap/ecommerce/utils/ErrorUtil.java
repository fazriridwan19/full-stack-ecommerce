package com.ursklap.ecommerce.utils;

import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
public class ErrorUtil {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse<?>> error(ResponseStatusException exception) {
        try {
            return ResponseEntity
                    .status(exception.getStatusCode())
                    .body(new ErrorResponse<String[]>(exception.getStatusCode().toString(), Objects.requireNonNull(exception.getReason()).split(", "), exception.getStatusCode().value()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(exception.getStatusCode())
                    .body(new ErrorResponse<String>(exception.getStatusCode().toString(), exception.getReason(), exception.getStatusCode().value()));
        }
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse<String>> error(JwtException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse<String>(HttpStatus.FORBIDDEN.toString(), exception.getMessage(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorResponse<String>> error(HttpClientErrorException.Unauthorized exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse<String>("Unauthorized", exception.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse<String>> error(AccessDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse<String>("Forbidden", exception.getMessage(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse<String>> error(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse<String>(HttpStatus.UNAUTHORIZED.getReasonPhrase(), exception.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<String>> error(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponse<String>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> error(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Internal server error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ErrorResponse<T> {
        private String error;
        private T message;
        private Integer code;
    }
}
