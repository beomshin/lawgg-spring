package com.kr.lg.service.sign;

import com.kr.lg.web.common.layer.SignLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SignEnrollService {

    @Transactional
    void userSign(SignLayer signLayer) throws Exception;
}
