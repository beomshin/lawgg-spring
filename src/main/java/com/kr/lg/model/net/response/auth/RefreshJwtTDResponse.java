package com.kr.lg.model.net.response.auth;

import com.kr.lg.model.common.root.DefaultResponse;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RefreshJwtTDResponse extends DefaultResponse { // RefreshJwtTokenDefaultResponse

    private String accessToken;
    private String expiredTime;

    public RefreshJwtTDResponse(String accessToken, Date expiredTime)  {
        this.accessToken = accessToken;
        this.expiredTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiredTime);
    }
}
