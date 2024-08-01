package com.kr.lg.module.thirdparty.service.impl;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.MailTb;
import com.kr.lg.enums.VerificationEnum;
import com.kr.lg.db.repositories.MailRepository;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.exception.ThirdPartyResultCode;
import com.kr.lg.module.thirdparty.model.req.SendEmailRequest;
import com.kr.lg.module.thirdparty.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final MailRepository mailRepository;

    @Override
    @Transactional
    public MailTb sendEmail(SendEmailRequest request) throws ThirdPartyException {
        try {
            log.info("▶ [메일] 메일 전송 내역 저장");
            MailTb mailTb = MailTb.builder()
                    .txId(UUID.randomUUID().toString())
                    .receiver(request.getEmail())
                    .code(ClientUtils.excuteGenerate())
                    .expired(new Date(System.currentTimeMillis() + (180 * 1000)))
                    .build();
            return mailRepository.save(mailTb);
        } catch (Exception e) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_INSERT_MAIL);
        }
    }

    @Override
    @Transactional
    public void verifyEmail(String txId, String code) throws ThirdPartyException {
        Optional<MailTb> mailTb = mailRepository.findByCodeAndTxIdAndExpiredAfterAndVerification(code, txId, new Date(), VerificationEnum.NON_COMPLETE);
        if (!mailTb.isPresent()) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_VERIFY_EMAIL);
        }
        try {
            log.info("▶ [메일] 메일 인증 내역 저장");
            mailRepository.updateCompleteVerifyEmail(mailTb.get().getMailId(), VerificationEnum.COMPLETE);
        } catch (Exception e) {
            throw new ThirdPartyException(ThirdPartyResultCode.FAIL_VERIFY_MAIL);
        }
    }

}
