package com.hyeonson.yajalal.exception;

import com.hyeonson.yajalal.dto.DefaultRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e){
        log.error("internal server error: " + e.toString());
        return ResponseEntity.status(500).body(e.toString());
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e){
        log.error("internal server error: " + e.toString());
        return ResponseEntity.status(500).body(e.toString());
    }
}
