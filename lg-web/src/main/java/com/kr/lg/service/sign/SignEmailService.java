package com.kr.lg.service.sign;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.SignLayer;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional(readOnly = true)
public interface SignEmailService {

    @Transactional
    DefaultResponse sendEmailSign(SignLayer signLayer) throws LgException, IOException;

    @Transactional
    void verifyEmailSign(SignLayer signLayer) throws LgException;
}
