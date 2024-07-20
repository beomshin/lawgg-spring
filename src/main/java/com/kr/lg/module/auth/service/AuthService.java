package com.kr.lg.module.auth.service;

import com.kr.lg.module.auth.excpetion.AuthException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional(readOnly = true)
public interface AuthService {
    String reissue(String refreshToken) throws AuthException;

    Date getExpiredTime(String token) throws AuthException;

}
