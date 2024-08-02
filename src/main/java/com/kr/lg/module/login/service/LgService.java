package com.kr.lg.module.login.service;

import com.kr.lg.module.login.model.lg.LgLoginDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LgService {

    @Transactional
    String LgLogin(LgLoginDto lgLoginDto) throws Exception;
}
