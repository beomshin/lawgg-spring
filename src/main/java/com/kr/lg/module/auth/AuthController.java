package com.kr.lg.module.auth;

import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.model.res.RefreshTokenResponse;
import com.kr.lg.module.auth.service.AuthService;
import com.kr.lg.web.common.root.AbstractSpec;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final String COOKIE_ID = "_lg_4SD343"; // 리프레쉬 토큰 쿠키 아이디

    private final AuthService authService;

    @ApiOperation(value = "인증 토큰 재발급", notes = "인증 토큰을 재발급 합니다.")
    @GetMapping("/api/public/v1/reissue/token")
    public ResponseEntity<?> reissueToken(@ApiParam(value = "인증 토큰", required = true) @CookieValue(value = COOKIE_ID) String refreshToken) throws AuthException {
        String token = authService.reissue(refreshToken); // 토큰 재발급
        Date expire = authService.getExpiredTime(token); // 만료일 조회

        AbstractSpec spec = RefreshTokenResponse.builder()
                .accessToken(token)
                .expiredTime(expire)
                .build();

        return ResponseEntity.ok(spec);
    }


}
