package com.kr.lg.service.auth.impl;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.crypto.AESCrypt;
import com.kr.lg.crypto.KeyManager;
import com.kr.lg.entities.UserTb;
import com.kr.lg.enums.entity.element.AuthEnum;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.sns.google.GoogleLoginRequestDto;
import com.kr.lg.model.web.common.sns.google.GoogleLoginResponseDto;
import com.kr.lg.repositories.UserRepository;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.service.auth.AuthService;
import com.kr.lg.service.jwt.JwtService;
import com.kr.lg.model.web.net.request.auth.DanalCRequest;
import com.kr.lg.model.web.net.response.auth.RefreshJwtTDResponse;
import com.kr.lg.provider.common.CookieProvider;
import com.kr.lg.utils.RestPortOne;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final CookieProvider cookieProvider;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RestPortOne restPortOne;

    @Value("${portone.rest.key}")
    private String portoneKey;

    @Value("${portone.rest.secret}")
    private String portoneSecret;


    @Override
    public DefaultResponse refreshJwtToken(String refreshToken) {

        // 1. access token에 닮겨 있는 userId를 가져옵니다.
        String userId = jwtService.getUserId(refreshToken);
        String type = jwtService.getType(refreshToken);

        // 2. refresh token 검증
        if (!jwtService.validateToken(refreshToken)) {
            cookieProvider.removeRefreshTokenCookie();
            throw new RuntimeException("Not validate jwt token = " + refreshToken);
        }

        userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        String newAccessToken = jwtService.generateAccessToken(userId, "/", jwtService.getRoles(refreshToken));
        Date expiredTime = jwtService.getExpiredTime(newAccessToken);
        return new RefreshJwtTDResponse(newAccessToken, expiredTime);
    }

    @Override
    public Boolean certificationsDanal(DanalCRequest request, UserTb userTb) throws LgException {
        if (!request.getSuccess()) {
            log.error("{}", request.getError_msg());
            return false;
        }
        HashMap<String, Object> map = restPortOne.getPersonalInfo(request.getImp_uid());
        userRepository.updateAuth((String) map.get("unique_key"), (String)map.get("unique_in_site")
                , (String)map.get("name"), (String) map.get("gender"), ((String) map.get("birthday")).replace("-", ""), AuthEnum.AUTH_STATUS, userTb.getUserId());
        return true;
    }

    @Override
    public String certificationsDanal(DanalCRequest request) throws Exception {
        if (!request.getSuccess()) {
            log.error("{}", request.getError_msg());
            throw new LgException(GlobalCode.FAIL_CERTIFICATION);
        }
        return AESCrypt.encrypt(new ObjectMapper().writeValueAsString(restPortOne.getPersonalInfo(request.getImp_uid())), KeyManager.aesKey);
    }

}
