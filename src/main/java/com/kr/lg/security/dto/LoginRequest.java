package com.kr.lg.security.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    private String loginId; // 로그인 아이디
    private String password; // 패스워드

    public boolean isRequestBlank() {
        return StringUtils.isBlank(this.loginId) || StringUtils.isBlank(this.password);
    }

}
