package com.kr.lg.service.sns;

import com.kr.lg.web.common.sns.lg.LgLoginDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LgService {

    @Transactional
    String LgLogin(LgLoginDto lgLoginDto) throws Exception;
}
