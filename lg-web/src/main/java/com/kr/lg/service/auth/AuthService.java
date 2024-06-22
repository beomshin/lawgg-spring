package com.kr.lg.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.entities.UserTb;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.net.request.auth.DanalCRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AuthService {
    DefaultResponse refreshJwtToken(String refreshToken);

    @Transactional
    Boolean certificationsDanal(DanalCRequest request, UserTb userTb) throws LgException;

    @Transactional
    String certificationsDanal(DanalCRequest request) throws Exception;
}
