package com.kr.lg.module.thirdparty;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.res.SendEmailResponse;
import com.kr.lg.module.thirdparty.service.MailService;
import com.kr.lg.web.dto.root.AbstractSpec;
import com.kr.lg.module.thirdparty.model.req.SendEmailRequest;
import com.kr.lg.module.thirdparty.service.EmailService;
import com.kr.lg.web.dto.root.SuccessResponse;
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
public class EmailController {

    private final EmailService emailService;
    private final MailService mailService;

    @PostMapping("/api/public/v1/send/email")
    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 인증번호를 전송합니다.")
    public ResponseEntity<?> sendEmail(
            @RequestBody SendEmailRequest request
    ) throws ThirdPartyException, IOException {
        MailTb mailTb = emailService.sendEmail(request);
        mailService.postAuthCode(request.getEmail(), mailTb.getCode(), "Law.gg 회원가입 인증번호입니다.");
        AbstractSpec spec = SendEmailResponse.builder()
                .txId(mailTb.getTxId())
                .build();
        return ResponseEntity.ok(spec);
    }

    @GetMapping("/api/public/v1/verify/email/{txId}")
    @ApiOperation(value = "이메일 인증번호 확인", notes = "이메일 인증번호를 확인합니다.")
    public ResponseEntity<?> verifyEmail(
            @ApiParam(value = "이메일 인증 트랜잭션 아이디", required = true) @PathVariable("txId") String txId,
            @ApiParam(value = "이메일 인증번호", required = true) @RequestParam(name = "code") String code
    ) throws ThirdPartyException {
        emailService.verifyEmail(txId, code);
        return ResponseEntity.ok(new SuccessResponse());
    }

}
