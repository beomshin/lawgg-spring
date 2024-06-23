package com.kr.lg.web.common.sns.kakao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoProfile {

    private String nickname;
    private String thumbnailImageUrl;
    private String profileImageUrl;
    private String isDefaultImage;
    private String isDefaultNickname;
}
