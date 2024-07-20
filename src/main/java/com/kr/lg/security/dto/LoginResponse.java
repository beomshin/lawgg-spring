package com.kr.lg.security.dto;

import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;


@Getter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse extends AbstractSpec {

    private String accessToken; // 인증 토큰
    private String refreshToken; // 리프레시 토큰
}
