package com.kr.lg.module.comment;

import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.model.enums.GlobalResultCode;
import com.kr.lg.model.common.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.kr.lg.module.comment")
@RequiredArgsConstructor
@Slf4j
public class CommentExceptionAdvice {

    // 커스텀 CommentException
    @ExceptionHandler(value = CommentException.class)
    public ModelAndView handle(CommentException e, HttpServletRequest request) {
        log.error("[CommentException] 오류 발생", e);
        ModelAndView mav = new ModelAndView("redirect:" + request.getHeader("Referer"));
        mav.addObject("error", new ErrorResponse(e.getResultCode()));
        return mav;
    }

    // @Validate 실패시
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ModelAndView handle(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("[BoardException] 오류 발생", e);
        ModelAndView mav = new ModelAndView("redirect:" + request.getHeader("Referer"));
        mav.addObject("error", new ErrorResponse(GlobalResultCode.PARAMETER_ERROR));
        return mav;
    }

    // 쿼리 파라미터의 유효성이 실패할경우의 예외에 대한 처리 코드
    @ExceptionHandler(value = BindException.class)
    public ModelAndView handle(BindException e, HttpServletRequest request) {
        log.error("[BindException] 오류 발생", e);
        ModelAndView mav = new ModelAndView("redirect:" + request.getHeader("Referer"));
        mav.addObject("error", new ErrorResponse(GlobalResultCode.PARAMETER_ERROR));
        return mav;
    }

    // @RequestBody 어노테이션이 붙은 매개변수에 대해 HTTP 요청의 본문이 없거나 잘못된 형식으로 인식되었을 때 나타나는 에러
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ModelAndView handle(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("[BindException] 오류 발생", e);
        ModelAndView mav = new ModelAndView("redirect:" + request.getHeader("Referer"));
        mav.addObject("error", new ErrorResponse(GlobalResultCode.PARAMETER_ERROR));
        return mav;
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handle(AccessDeniedException e) {
        log.error("[AccessDeniedException] 오류 발생", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(GlobalResultCode.ACCESS_DENIED_ERROR));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView handle(RuntimeException e, HttpServletRequest request) {
        log.error("[RuntimeException] 오류 발생", e);
        ModelAndView mav = new ModelAndView("redirect:" + request.getHeader("Referer"));
        mav.addObject("error", new ErrorResponse(GlobalResultCode.SYSTEM_ERROR));
        return mav;
    }

}
