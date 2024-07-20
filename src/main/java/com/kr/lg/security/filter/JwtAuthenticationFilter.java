package com.kr.lg.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.web.dto.root.ErrorResponse;
import com.kr.lg.service.jwt.JwtService;
import com.kr.lg.security.login.detail.JwtDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDetailService jwtDetailService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    /**
     * 인증 필터
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            this.isValid(jwtService.parseJwt(request), request.getRequestURI()); // jwt 인증
            filterChain.doFilter(request, response);
        } catch (AuthException e) {
            log.error("▶ [토큰 생성 에러] ===============> [{}]", e.getResultCode().getMsg());
            this.generateFailResponseBody(response, HttpStatus.UNAUTHORIZED, e.getResultCode().getCode(), e.getResultCode().getMsg());
        } catch (Exception e) {
            log.error("▶ [JwtAuthenticationFilter] Exception", e);
            this.generateFailResponseBody(response, HttpStatus.INTERNAL_SERVER_ERROR, GlobalCode.SYSTEM_ERROR.getCode(), GlobalCode.SYSTEM_ERROR.getMsg());
        }
    }

    /**
     * 토큰 유효성 검사 메소드 (/public 경로 접근은 SKIP 처리)
     *
     * 1. 토큰 누락
     * 2. 토큰 유효성
     * 3. 토큰 유저 정보 조회
     *
     * @param token
     * @param uri
     * @throws AuthException
     */
    private void isValid(String token, String uri) throws AuthException {
        log.info("{}", uri);
        if (uri.contains("/public") || uri.contains("swagger") || uri.contains("api-docs")) {
            return; // public 접근으로 간주
        }

        if (StringUtils.isBlank(token)) {
            log.error("▶ [인증 토큰] 토큰 누락 실패");
            throw new AuthException(AuthResultCode.NOT_EXIST_JWT_TOKEN);
        } else if (!jwtService.validate(token)) {
            log.error("▶ [인증 토큰] 토큰 유효성 검사 실패");
            throw new AuthException(AuthResultCode.FAIL_VALIDATE_JWT_TOKEN);
        }

        try {
            UserDetails userDetails = jwtDetailService.loadUserByUsername(jwtService.getUserId(token)); // user 조회
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); // security context authentication 주입
        } catch (Exception e) {
            log.error("▶ [인증 토큰] 유저 정보 조회 실패 - [{}]", e.getMessage());
            throw new AuthException(AuthResultCode.FAIL_FIND_USER_INFO);
        }
    }

    /**
     * 유효성 검사 실패 응답 생성 메소드
     * @param response
     * @param status
     * @param code
     * @param msg
     * @throws IOException
     */
    private void generateFailResponseBody(HttpServletResponse response, HttpStatus status, String code, String msg) throws IOException {
        response.setStatus(status.value()); // http status
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // content-type json 고정
        objectMapper.writeValue(response.getOutputStream(), new ErrorResponse(code, msg)); // 응답 body
    }

}
