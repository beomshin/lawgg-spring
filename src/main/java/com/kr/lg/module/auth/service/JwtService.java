package com.kr.lg.module.auth.service;

import com.kr.lg.module.auth.excpetion.AuthException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface JwtService {

    String createJwtToken(String subject, String uri, List<String> roles);
    String createRefreshToken(String subject, String uri, List<String> roles);
    boolean validate(String token);
    Date getExpiredTime(String token) throws AuthException;
    List<String> getRoles(String token) throws AuthException;
    String getUserId(String token) throws AuthException;
    String getType(String token) throws AuthException;
    String parseJwt(HttpServletRequest request);
}
