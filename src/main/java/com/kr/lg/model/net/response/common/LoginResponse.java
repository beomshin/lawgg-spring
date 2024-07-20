package com.kr.lg.model.net.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends DefaultResponse {

    private String accessToken;
    private String refreshToken;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date expiredTime;

}
