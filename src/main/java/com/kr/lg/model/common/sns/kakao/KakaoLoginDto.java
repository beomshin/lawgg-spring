package com.kr.lg.model.common.sns.kakao;

import com.kr.lg.model.common.sns.kakao.dto.KakaoAcount;
import com.kr.lg.model.common.sns.kakao.dto.KakaoProperties;
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