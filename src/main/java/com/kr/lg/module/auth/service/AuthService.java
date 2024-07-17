package com.kr.lg.module.auth.service;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.net.request.auth.DanalCRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional(readOnly = true)
public interface AuthService {
    String reissue(String refreshToken) throws AuthException;

    Date getExpiredTime(String token) throws AuthException;

}
