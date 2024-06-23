package com.kr.lg.web.common.sns.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaverLoginResponseDto {
    private String accessToken; // 애플리케이션이 네이버 API 요청을 승인하기 위해 보내는 토큰
    private String refreshToken;    // 새 액세스 토큰을 얻는 데 사용할 수 있는 토큰
    private String tokenType;   // 반환된 토큰 유형(Bearer 고정)
    private String expiresIn;   // Access Token의 남은 수명

}
