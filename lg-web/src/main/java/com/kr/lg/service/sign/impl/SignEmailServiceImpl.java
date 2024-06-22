package com.kr.lg.service.sign.impl;

import com.kr.lg.utils.ClientUtils;
import com.kr.lg.entities.MailTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.VerificationEnum;
import com.kr.lg.repositories.MailRepository;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.common.service.mail.MailService;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.layer.SignLayer;
import com.kr.lg.model.web.net.response.sign.SignSEResponse;
import com.kr.lg.service.sign.SignEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignEmailServiceImpl implements SignEmailService {

    private final MailRepository mailRepository;
    private final MailService mailService;
    @Override
    public DefaultResponse sendEmailSign(SignLayer signLayer) throws IOException {
        MailTb mailTb = MailTb.builder()
                .txId(UUID.randomUUID().toString())
                .receiver(signLayer.getEmail())
                .code(ClientUtils.excuteGenerate())
                .expired(new Date(System.currentTimeMillis() + (180 * 1000)))
                .build();
        mailRepository.save(mailTb);
        mailService.postAuthCode(signLayer.getEmail(), mailTb.getCode());
        return new SignSEResponse(mailTb.getTxId());
    }

    @Override
    public void verifyEmailSign(SignLayer signLayer) throws LgException {
        MailTb mailTb = mailRepository.findByCodeAndTxIdAndExpiredAfter(signLayer.getCode(), signLayer.getTxId(), new Date()).orElseThrow(() -> new LgException(GlobalCode.FAIL_VERIFY_EMAIL));
        if (mailTb.getVerification().equals(VerificationEnum.COMPLETE)) throw new LgException(GlobalCode.ALREADY_VERIFY_EMAIL);
        mailRepository.finishVerification(mailTb.getMailId(), VerificationEnum.COMPLETE);
    }
}
