package com.kr.lg.common.provider;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


@Component
public class CookieProvider {

    public void removeRefreshTokenCookie() {
        ResponseCookie.from("refresh-token", null).build();
    }

}
