package com.kr.lg.service.sign;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.SignLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SignCheckService {

    void checkIdSign(SignLayer signLayer) throws LgException;
    void checkNickNameSign(SignLayer signLayer) throws LgException;
}
