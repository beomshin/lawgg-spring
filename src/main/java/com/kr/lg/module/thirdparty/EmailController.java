package com.kr.lg.module.thirdparty;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.res.SendEmailResponse;
import com.kr.lg.module.thirdparty.service.MailService;
import com.kr.lg.model.common.AbstractSpec;
import com.kr.lg.module.thirdparty.model.req.SendEmailRequest;
import com.kr.lg.module.thirdparty.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "EmailController", description = "이메일 컨트롤러")
public class EmailController {

    private final EmailService emailService;
    private final MailService mailService;

    @PostMapping("/send/email")
    @Operation(summary = "이메일 인증번호 전송", description = "이메일 인증번호를 전송합니다.")
    public ResponseEntity<?> sendEmail(
            @RequestBody @Valid SendEmailRequest request
    ) throws ThirdPartyException, IOException { // 미사용
        MailTb mailTb = emailService.sendEmail(request);
        mailService.postAuthCode(request.getEmail(), mailTb.getCode(), "Law.gg 회원가입 인증번호입니다.");
        AbstractSpec spec = SendEmailResponse.builder()
                .txId(mailTb.getTxId())
                .build();
        return ResponseEntity.ok(spec);
    }

}
