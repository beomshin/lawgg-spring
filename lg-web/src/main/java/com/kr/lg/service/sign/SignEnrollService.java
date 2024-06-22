package com.kr.lg.service.sign;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.SignLayer;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Transactional(readOnly = true)
public interface SignEnrollService {

    @Transactional
    void userSign(SignLayer signLayer) throws Exception;
}
