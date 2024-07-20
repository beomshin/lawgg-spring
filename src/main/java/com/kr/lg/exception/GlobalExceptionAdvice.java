package com.kr.lg.exception;

import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.kr.lg.controller")
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<DefaultResponse> handle(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException]", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultResponse(GlobalCode.PARAMETER_ERROR));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultResponse> handle(BindException e) {
        log.error("[BindException]", e);
        return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultResponse> handle(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]", e);
        return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
    }


    @ExceptionHandler
    public ResponseEntity<DefaultResponse> handle(RuntimeException e) {
        log.error("[RuntimeException]", e);
        return ResponseEntity.ok().body(new DefaultResponse(GlobalCode.SYSTEM_ERROR));
    }

    @ExceptionHandler(value = LgException.class)
    public ResponseEntity<DefaultResponse> handle(LgException e) {
        log.error("[LgException]", e);
        return ResponseEntity.ok().body(new DefaultResponse(e.getCode()));
    }

}
