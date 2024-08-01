package com.kr.lg.module.thirdparty.service;

import java.io.IOException;

public interface MailService {
    void postAuthCode(String receiver, String code, String title) throws IOException;
}
