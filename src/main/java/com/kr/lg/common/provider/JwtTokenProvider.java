package com.kr.lg.common.provider;

import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtTokenProvider {

    /**
     * JWT 토큰 생성
     * @param key
     * @param claims
     * @param uri
     * @param expiration
     * @return
     */
    public String createJwtToken(String key, Claims claims, String uri, Date expiration) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(expiration)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .setIssuer(uri)
                .compact();
    }

    /**
     * JWT 유효성 검사
     * @param key
     * @param token
     * @return
     */
    public boolean validateJwtToken(String key, String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * JWT 만료일 조회
     * @param key
     * @param accessToken
     * @return
     * @throws AuthException
     */
    public Date getExpiredTime(String key, String accessToken) throws AuthException {
        try {
            return this.getClaims(key, accessToken).getExpiration();
        } catch (Exception e) {
            throw new AuthException(AuthResultCode.FAIL_FIND_TOKEN_INFO);
        }
    }

    /**
     * JWT Subject 조회
     * @param key
     * @param token
     * @return
     * @throws AuthException
     */
    public String getSubject(String key, String token) throws AuthException {
        try {
            return this.getClaims(key, token).getSubject();
        } catch (Exception e) {
            throw new AuthException(AuthResultCode.FAIL_FIND_TOKEN_INFO);
        }
    }

    /**
     * JWT type 조회
     * @param key
     * @param token
     * @return
     * @throws AuthException
     */
    public String getType(String key, String token) throws AuthException {
        try {
            return this.getClaims(key,token).get("type").toString();
        } catch (Exception e) {
            throw new AuthException(AuthResultCode.FAIL_FIND_TOKEN_INFO);
        }
    }

    /**
     * JWT Role 조회
     * @param key
     * @param token
     * @return
     * @throws AuthException
     */
    public List<String> getRoles(String key, String token) throws AuthException {
        try {
            if (this.getClaims(key, token).containsKey("roles")) {
                return (List<String>) this.getClaims(key, token).get("roles");
            } else {
                return null;
            }
        } catch (ClassCastException e) {
            throw new AuthException(AuthResultCode.FAIL_FIND_TOKEN_INFO);
        }
    }

    /**
     * Claims 조회
     * @param key
     * @param token
     * @return
     */
    private Claims getClaims(String key, String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
