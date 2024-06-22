package com.kr.lg.model.web.net.request.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class LoginRequest {
    private String loginId;
    private String password;


    public boolean isRequestBlank() {
        return StringUtils.isBlank(this.loginId) || StringUtils.isBlank(this.password);
    }
}
