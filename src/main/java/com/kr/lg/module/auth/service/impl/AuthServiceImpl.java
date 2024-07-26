package com.kr.lg.module.auth.service.impl;


import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.auth.service.AuthService;
import com.kr.lg.service.jwt.JwtService;
import com.kr.lg.common.provider.CookieProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final CookieProvider cookieProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public String reissue(String refreshToken) throws AuthException {
        log.info("▶ [인증 토큰] reissue 메소드 실행");

        log.info("▶ [인증 토큰] 토큰 재발급 메소드 실행");

        // 1. refresh token 검증
        if (!jwtService.validate(refreshToken)) {
            log.error("▶ [인증 토큰] 리프레쉬 토큰 유효성 검사 실패");
            cookieProvider.removeRefreshTokenCookie();
            throw new AuthException(AuthResultCode.FAIL_VALIDATE_JWT_TOKEN);
        }

        // 2. access token에 닮겨 있는 userId를 가져옵니다.
        String userId = jwtService.getUserId(refreshToken);
        UserTb userTb = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new AuthException(AuthResultCode.FAIL_FIND_USER_INFO));
        log.info("▶ [인증 토큰] {}({}) 유저 토큰 재발급", userTb.getNickName(), userTb.getUserId());

        return jwtService.createJwtToken(userId, "/", jwtService.getRoles(refreshToken));
    }

    @Override
    public Date getExpiredTime(String token) throws AuthException {
        log.info("▶ [인증 토큰] getExpiredTime 메소드 실행");

        log.info("▶ [인증 토큰] 토큰 만료일 조회");
        return jwtService.getExpiredTime(token);
    }

}
