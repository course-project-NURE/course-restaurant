package com.restaurant.course.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviser {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleControllerException(
            ResponseStatusException exception, ServletWebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", exception.getRawStatusCode());
        body.put("error", exception.getStatus());
        body.put("message", exception.getReason());
        body.put("path", request.getRequest().getRequestURI());

        return new ResponseEntity<>(body, exception.getStatus());
    }
}
