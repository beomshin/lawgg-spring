package com.kr.lg.security.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.security.exception.SecurityException;
import com.kr.lg.web.dto.root.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    /**
     * 로그인 실패 핸들러
     * @param request the request during which the authentication attempt occurred.
     * @param response the response.
     * @param exception the exception which was thrown to reject the authentication
     * request.
     * @throws IOException
     */

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("▶ [Spring Security 로그인][LoginFailHandler] 로그인 실패");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // content-type json
        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(getFailCode(exception))); // 응답 body
    }

    /**
     * 로그인 실패 사유 세팅 메소드
     * @param exception
     * @return
     */
    private AuthResultCode getFailCode(AuthenticationException exception) {
        if (exception instanceof AuthenticationServiceException) { // 로그인 정보 누락 && 로그인 아이디 미존재
            log.error("▶ [Spring Security 로그인][LoginFailHandler] 미존재 아이디");
            return AuthResultCode.NOT_EXIST_USER;
        } else if(exception instanceof BadCredentialsException) { // 비밀번호 불일치 에러
            log.error("▶ [Spring Security 로그인][LoginFailHandler] 비밀번호 불일치");
            return AuthResultCode.UN_MATCHED_PASSWORD;
        } else if (exception instanceof UsernameNotFoundException) {

            if (exception.getCause() instanceof SecurityException) {
                SecurityException e = ((SecurityException) exception.getCause());
                log.error("▶ [Spring Security 로그인][LoginFailHandler] {}", e.getResultCode().getMsg());
                return e.getResultCode(); // 로그인 실패 (정지, 삭제 계정)
            }

        }

        return AuthResultCode.FAIL_LOGIN; // 사유 확인 필요 로그인 실패
    }
}
