package com.kr.lg.common.service.mail;

import com.kr.lg.utils.SmtpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

@Service
@Slf4j
public class MailServiceImpl implements MailService{

    private final String ROOT_PATH = "classpath:mail";
    private final String HTML_NAME = "mail_authentication_code.html";
    private final SmtpUtils smtpUtils;
    private final String MAIL_HTML;

    public MailServiceImpl(SmtpUtils smtpUtils, ResourceLoader resourceLoader) throws IOException {
        this.smtpUtils = smtpUtils;
        this.MAIL_HTML = FileCopyUtils.copyToString(new InputStreamReader(resourceLoader.getResource(ROOT_PATH + "/" + HTML_NAME).getInputStream()));
    }

    @Override
    public void postAuthCode(String receiver, String code) {
        post(receiver, code, "Law.gg 회원가입 인증번호입니다.");
    }

    @Override
    public void postAuthCode(String receiver, String code, String title) {
        post(receiver, code, title);
    }

    private void post(String receiver, String code, String title) {
        HashMap<String, String> mailParam = new HashMap<>();
        StrSubstitutor strSubstitutor = new StrSubstitutor(mailParam);
        mailParam.put("CODE", code);
        mailParam.put("RECEIVER", receiver);
        smtpUtils.postAsync(receiver, title, strSubstitutor.replace(MAIL_HTML));
    }

}
