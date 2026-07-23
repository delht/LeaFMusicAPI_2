package de.lht.leafmusic3.exception;

import de.lht.leafmusic3.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException ex) {

        ApiResponse<Object> response =
                new ApiResponse<>(
                        ex.getStatus(),
                        ex.getMessage(),
                        null
                );

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }
}