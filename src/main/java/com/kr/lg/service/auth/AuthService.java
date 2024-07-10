package com.kr.lg.service.auth;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.net.request.auth.DanalCRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AuthService {
    DefaultResponse refreshJwtToken(String refreshToken);

    @Transactional
    Boolean certificationsDanal(DanalCRequest request, UserTb userTb) throws LgException;

    @Transactional
    String certificationsDanal(DanalCRequest request) throws Exception;
}