package com.kr.lg.security.login.handler;

import com.kr.lg.security.exception.SecurityResultCode;
import com.kr.lg.security.exception.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 로그인 실패 핸들러
     * @param request the request during which the authentication attempt occurred.
     * @param response the response.
     * @param exception the exception which was thrown to reject the authentication
     * request.
     * @throws IOException
     */

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        log.error("▶ [Spring Security 로그인][LoginFailHandler] 로그인 실패");
        setDefaultFailureUrl("/login?error=true&message=" + getFailMessage(getFailCode(exception)));
        super.onAuthenticationFailure(request, response, exception);
    }

    /**
     * 로그인 실패 사유 세팅 메소드
     * @param exception
     * @return
     */
    private SecurityResultCode getFailCode(AuthenticationException exception) {
        if (exception instanceof AuthenticationServiceException) { // 로그인 정보 누락 && 로그인 아이디 미존재
            log.error("▶ [Spring Security 로그인][LoginFailHandler] 미존재 아이디");
            return SecurityResultCode.NOT_EXIST_USER;
        } else if(exception instanceof BadCredentialsException) { // 비밀번호 불일치 에러
            log.error("▶ [Spring Security 로그인][LoginFailHandler] 비밀번호 불일치");
            return SecurityResultCode.UN_MATCHED_PASSWORD;
        } else if (exception instanceof UsernameNotFoundException) {

            if (exception.getCause() instanceof SecurityException) {
                SecurityException e = ((SecurityException) exception.getCause());
                log.error("▶ [Spring Security 로그인][LoginFailHandler] {}", e.getResultCode().getMsg());
                return e.getResultCode(); // 로그인 실패 (정지, 삭제 계정)
            }

        }

        return SecurityResultCode.FAIL_LOGIN; // 사유 확인 필요 로그인 실패
    }

    private String getFailMessage(SecurityResultCode code) throws UnsupportedEncodingException {
        String message = "";
        if (code == SecurityResultCode.NOT_EXIST_USER) {
            message = "아이디를 다시 확인해주세요.";
        } else if (code == SecurityResultCode.UN_MATCHED_PASSWORD) {
            message = "비밀번호를 다시 확인해주세요.";
        } else if (code == SecurityResultCode.LOCK_LOGIN_ID) {
            message = "정지된 계정입니다. 계정 확인을 위해서는 관리자에게 문의해주세요.";
        } else if (code == SecurityResultCode.DELETE_LOGIN_ID) {
            message = "삭제된 계정입니다.";
        } else {
            message = "로그인에 실패하였습니다. 잠시 후 진행해주세요.";
        }
        return URLEncoder.encode(message, "UTF-8");
    }
}
