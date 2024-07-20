package com.kr.lg.module.auth.model.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;

import java.util.Date;


@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RefreshTokenResponse extends AbstractSpec {

    private String accessToken; // 재발급 토큰

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date expiredTime; // 토큰 만료일


}
