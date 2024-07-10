package com.kr.lg.controller.sign;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.SignLayer;
import com.kr.lg.model.net.request.sign.SendERequest;
import com.kr.lg.service.sign.SignEmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignEmailController {

    private final SignEmailService signEmailService;

    @PostMapping("/api/public/sign/send/email")
    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 인증번호를 전송합니다.")
    public ResponseEntity<DefaultResponse> sendEmailSign(
            @RequestBody SendERequest request
    ) throws LgException, IOException {
        DefaultResponse body = signEmailService.sendEmailSign(new SignLayer(request));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/api/public/sign/verify/email/{txId}")
    @ApiOperation(value = "이메일 인증번호 확인", notes = "이메일 인증번호를 확인합니다.")
    public ResponseEntity<DefaultResponse> verifyEmailSign(
            @ApiParam(value = "이메일 인증 트랜잭션 아이디", required = true) @PathVariable("txId") String txId,
            @ApiParam(value = "이메일 인증번호", required = true) @RequestParam(name = "code") String code
    ) throws LgException {
        signEmailService.verifyEmailSign(new SignLayer(txId, code));
        return ResponseEntity.ok(new DefaultResponse());
    }

}
