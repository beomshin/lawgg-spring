package com.kr.lg.module.board;

import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.web.dto.root.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.kr.lg.module.board")
@RequiredArgsConstructor
@Slf4j
public class BoardExceptionAdvice {

    // 커스텀 BoardException
    @ExceptionHandler(value = BoardException.class)
    public ResponseEntity<?> handle(BoardException e) {
        log.error("[BoardException] 오류 발생", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getResultCode()));
    }

    // @Validate 실패시
    @ExceptionHandler
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException]: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(GlobalCode.PARAMETER_ERROR));
    }

    // 쿼리 파라미터의 유효성이 실패할경우의 예외에 대한 처리 코드
    @ExceptionHandler
    public ResponseEntity<?> handle(BindException e) {
        log.error("[BindException]: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(GlobalCode.PARAMETER_ERROR));
    }

    // @RequestBody 어노테이션이 붙은 매개변수에 대해 HTTP 요청의 본문이 없거나 잘못된 형식으로 인식되었을 때 나타나는 에러
    @ExceptionHandler
    public ResponseEntity<?> handle(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(GlobalCode.PARAMETER_ERROR));
    }


    @ExceptionHandler
    public ResponseEntity<?> handle(RuntimeException e) {
        log.error("[RuntimeException]", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(GlobalCode.SYSTEM_ERROR));
    }


}
