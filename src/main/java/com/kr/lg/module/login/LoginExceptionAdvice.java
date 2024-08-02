package com.kr.lg.module.login;

import com.kr.lg.module.login.exception.LoginException;
import com.kr.lg.module.user.excpetion.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.view.RedirectView;

@RestControllerAdvice(basePackages = "com.kr.lg.module.login")
@RequiredArgsConstructor
@Slf4j
public class LoginExceptionAdvice {

    @Value("${lg.redirect.url.login}")
    String lgRedirectLoginUrl;

    // 커스텀 LoginException
    @ExceptionHandler(value = LoginException.class)
    public RedirectView handle(UserException e) {
        log.error("[LoginException] 오류 발생", e);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(lgRedirectLoginUrl);
        return redirectView;
    }


    // @Validate 실패시
    @ExceptionHandler
    public RedirectView handle(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException]: {}", e.getMessage());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(lgRedirectLoginUrl);
        return redirectView;
    }

    // 쿼리 파라미터의 유효성이 실패할경우의 예외에 대한 처리 코드
    @ExceptionHandler
    public RedirectView handle(BindException e) {
        log.error("[BindException]: {}", e.getMessage());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(lgRedirectLoginUrl);
        return redirectView;
    }

    // @RequestBody 어노테이션이 붙은 매개변수에 대해 HTTP 요청의 본문이 없거나 잘못된 형식으로 인식되었을 때 나타나는 에러
    @ExceptionHandler
    public RedirectView handle(HttpMessageNotReadableException e) {
        log.error("[HttpMessageNotReadableException]: {}", e.getMessage());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(lgRedirectLoginUrl);
        return redirectView;
    }

    @ExceptionHandler
    public RedirectView handle(RuntimeException e) {
        log.error("[RuntimeException]", e);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(lgRedirectLoginUrl);
        return redirectView;
    }



}
