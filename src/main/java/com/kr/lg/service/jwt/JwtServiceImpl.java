package com.kr.lg.service.jwt;

import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.common.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${token.access-expired-time}")
    private long ACCESS_EXPIRED_TIME;

    @Value("${token.refresh-expired-time}")
    private long REFRESH_EXPIRED_TIME;

    @Value("${token.key}")
    private String jwtKey;

    /**
     * jwt 토큰 생성 메소드
     * @param subject
     * @param uri
     * @param roles
     * @return
     */
    @Override
    public String createJwtToken(String subject, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("roles", roles);
        claims.put("type", SnsType.LG_SNS_TYPE.getCode());
        return jwtTokenProvider.createJwtToken(jwtKey, claims, uri, new Date(System.currentTimeMillis() + (ACCESS_EXPIRED_TIME * 1000)));
    }

    /**
     * jwt 리프레시 토큰 생성 메소드
     * @param subject
     * @param uri
     * @param roles
     * @return
     */
    @Override
    public String createRefreshToken(String subject, String uri, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("roles", roles);
        claims.put("type", SnsType.LG_SNS_TYPE.getCode());
        return jwtTokenProvider.createJwtToken(jwtKey, claims, uri, new Date(System.currentTimeMillis() + (REFRESH_EXPIRED_TIME * 1000)));
    }

    /**
     * 토큰 유효성 검사 메소드
     * @param token
     * @return
     */
    @Override
    public boolean validate(String token) {
        return jwtTokenProvider.validateJwtToken(jwtKey, token);
    }

    /**
     * 토큰 만료일 조회 메소드
     * @param token
     * @return
     * @throws AuthException
     */
    @Override
    public Date getExpiredTime(String token) throws AuthException {
        return jwtTokenProvider.getExpiredTime(jwtKey, token);
    }

    /**
     * 토큰 role 조회 메소드
     * @param token
     * @return
     */
    @Override
    public List<String> getRoles(String token) throws AuthException {
        return jwtTokenProvider.getRoles(jwtKey, token);
    }

    /**
     * 토큰 아이디 조회 메소드
     * @param token
     * @return
     * @throws AuthException
     */
    @Override
    public String getUserId(String token) throws AuthException {
        return jwtTokenProvider.getSubject(jwtKey, token);
    }

    /**
     * 토큰 로그인 타입 조회 메소드
     * @param token
     * @return
     */
    @Override
    public String getType(String token) throws AuthException {
        return jwtTokenProvider.getType(jwtKey, token);
    }

    /**
     * 토큰 파싱 메소드
     * @param request
     * @return
     */
    @Override
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.isNoneEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

}
