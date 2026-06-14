package com.m42.template.common.exception;

import com.m42.template.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiResponse<Void>> notFound(NotFoundException ex, HttpServletRequest request) {
        return error(HttpStatus.NOT_FOUND, "not_found", ex.getMessage(), request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    ResponseEntity<ApiResponse<Void>> validation(Exception ex, HttpServletRequest request) {
        return error(HttpStatus.BAD_REQUEST, "validation_failed", ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<Void>> unexpected(Exception ex, HttpServletRequest request) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "internal_error", ex.getMessage(), request);
    }

    private ResponseEntity<ApiResponse<Void>> error(HttpStatus status, String code, String message, HttpServletRequest request) {
        String correlationId = (String) request.getAttribute("correlationId");
        return ResponseEntity.status(status).body(ApiResponse.failed(code, message, correlationId));
    }
}
