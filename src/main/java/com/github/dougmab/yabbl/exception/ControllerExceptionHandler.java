package com.github.dougmab.yabbl.exception;

import com.github.dougmab.yabbl.payload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<StandardError>> badCredentials(BadCredentialsException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(err.getStatus())
                .body(ApiResponse.error("Failed to login", err));
    }
}
