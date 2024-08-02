package com.kr.lg.module.thirdparty;

import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.thirdparty.model.req.CertificationsDanalRequest;
import com.kr.lg.module.thirdparty.model.res.PublicCertificationsDanalResponse;
import com.kr.lg.module.thirdparty.service.DanalService;
import com.kr.lg.model.global.AbstractSpec;
import com.kr.lg.model.global.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DanalController {

    private final DanalService danalService;

    @PostMapping("/api/v1/certifications/danal")
    @ApiOperation(value = "로그인 다날 본인 인증 요청", notes = "로그인 다날 본인 인증을 요청합니다.")
    public ResponseEntity<?> certificationsDanal(
            @RequestBody @Valid CertificationsDanalRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws ThirdPartyException { // 미사용
        danalService.certificationsDanal(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/public/v1/certifications/danal")
    @ApiOperation(value = "비로그인 다날 본인 인증 요청", notes = "비로그인 다날 본인 인증 요청을 전송합니다.")
    public ResponseEntity<?> publicCertificationsDanal(
            @RequestBody @Valid CertificationsDanalRequest request
    ) throws Exception { // 미사용
        String data = danalService.certificationsDanal(request);
        AbstractSpec spec = PublicCertificationsDanalResponse.builder()
                .data(data)
                .build();
        return ResponseEntity.ok().body(spec);
    }

}
