package com.kr.lg.module.login.model.kakao;

import com.kr.lg.module.login.model.kakao.dto.KakaoAcount;
import com.kr.lg.module.login.model.kakao.dto.KakaoProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoLoginDto {

    private String id;
    private String connectedAt;
    private KakaoProperties properties;
    private KakaoAcount kakaoAccount;

}
