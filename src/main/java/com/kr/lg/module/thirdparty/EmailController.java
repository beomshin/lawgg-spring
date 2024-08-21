package com.kr.lg.module.thirdparty;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.res.SendEmailResponse;
import com.kr.lg.module.thirdparty.service.MailService;
import com.kr.lg.model.common.AbstractSpec;
import com.kr.lg.module.thirdparty.model.req.SendEmailRequest;
import com.kr.lg.module.thirdparty.service.EmailService;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final MailService mailService;

    @PostMapping("/send/email")
    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 인증번호를 전송합니다.")
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
