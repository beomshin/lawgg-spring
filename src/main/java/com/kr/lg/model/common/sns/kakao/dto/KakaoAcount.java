package com.kr.lg.model.common.sns.kakao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoAcount {

    private String profileNicknameNeedsAgreement;
    private String profileImageNeedsAgreement;
    private KakaoProfile profile;
    private String hasEmail;
    private String emailNeedsAgreement;
    private String isEmailValid;
    private String isEmailVerified;
    private String email;
}
