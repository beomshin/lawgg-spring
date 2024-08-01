package com.kr.lg.module.thirdparty.service;

import com.kr.lg.db.entities.MailTb;
import com.kr.lg.module.thirdparty.exception.ThirdPartyException;
import com.kr.lg.module.thirdparty.model.req.SendEmailRequest;

public interface EmailService {
    MailTb sendEmail(SendEmailRequest request) throws ThirdPartyException;
    void verifyEmail(String txId, String code) throws ThirdPartyException;
}
