package com.kr.lg.model.common.sns.naver;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
public class NaverProp {

    @Value("${naver.auth.url}")
    private String naverAuthUrl;

    @Value("${naver.login.url}")
    private String naverLoginUrl;

    @Value("${naver.redirect.uri}")
    private String naverRedirectUri;

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.secret}")
    private String naverSecret;

    public String naverInitUrl() {
        SecureRandom random = new SecureRandom();

        Map<String, Object> params = new HashMap<>();
        params.put("client_id", getNaverClientId());
        params.put("redirect_uri", getNaverRedirectUri());
        params.put("response_type", "code");
        params.put("state", new BigInteger(130, random).toString(32));

        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));

        return getNaverLoginUrl()
                + "/oauth2.0/authorize"
                + "?"
                + paramStr;
    }

}
