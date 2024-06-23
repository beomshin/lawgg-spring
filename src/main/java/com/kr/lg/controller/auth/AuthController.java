package com.kr.lg.controller.auth;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.net.request.auth.DanalCRequest;
import com.kr.lg.web.net.response.auth.PublicCertificationsDanalResponse;
import com.kr.lg.service.auth.AuthService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/api/public/auth/reissue")
    public ResponseEntity<DefaultResponse> refreshToken(
            @ApiParam(value = "인증 리프레쉬 토큰", required = true) @CookieValue(value = "_lg_4SD343") String refreshToken) {
        DefaultResponse body = authService.refreshJwtToken(refreshToken);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/certifications/danal")
    public ResponseEntity certificationsDanal(@RequestBody DanalCRequest request, @UserPrincipal UserAdapter userAdapter) throws LgException {
        Boolean success = authService.certificationsDanal(request, userAdapter.getUserTb());
        DefaultResponse body = success ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_CERTIFICATION);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/api/public/certifications/danal")
    public ResponseEntity publicCertificationsDanal(@RequestBody DanalCRequest request) throws Exception {
        return ResponseEntity.ok().body(new PublicCertificationsDanalResponse(authService.certificationsDanal(request)));
    }
}
