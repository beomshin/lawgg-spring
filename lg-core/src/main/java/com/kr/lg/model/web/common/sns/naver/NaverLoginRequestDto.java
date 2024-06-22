package com.kr.lg.model.web.common.sns.naver;

import lombok.Data;

@Data
public class NaverLoginRequestDto {

    private String clientId;    // 애플리케이션의 클라이언트 ID
    private String redirectUri; // 로그인 후 redirect 위치
    private String clientSecret;    // 클라이언트 보안 비밀
    private String code;
    private String state;
    private String grantType;

    public NaverLoginRequestDto(NaverProp naverProp, String code, String state) {
        this.clientId = naverProp.getNaverClientId();
        this.redirectUri = naverProp.getNaverRedirectUri();
        this.clientSecret = naverProp.getNaverSecret();
        this.code = code;
        this.state = state;
        this.grantType = "authorization_code";
    }
}
