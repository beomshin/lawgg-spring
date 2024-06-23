package com.kr.lg.service.mail;

import java.io.IOException;

public interface MailService {

    void postAuthCode(String receiver, String code) throws IOException;

    void postAuthCode(String receiver, String code, String title) throws IOException;
}
