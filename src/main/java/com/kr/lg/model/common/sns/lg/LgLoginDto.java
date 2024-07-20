package com.kr.lg.model.common.sns.lg;

import com.kr.lg.model.common.sns.google.GoogleLoginDto;
import com.kr.lg.model.common.sns.kakao.KakaoLoginDto;
import com.kr.lg.model.common.sns.naver.NaverLoginDto;
import com.kr.lg.common.enums.entity.type.SnsType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class LgLoginDto {

    private String nickname;
    private String snsId;
    private String name;
    private String email;
    private SnsType snsType;
    private String profile;
    private String loginId;

    public LgLoginDto(GoogleLoginDto googleLoginDto) {
        this.nickname = googleLoginDto.getName();
        this.snsId = googleLoginDto.getSub();
        this.name = googleLoginDto.getName();
        this.email = googleLoginDto.getEmail();
        this.profile = googleLoginDto.getPicture();
        this.snsType = SnsType.GOOGLE_SNS_TYPE;
    }

    public LgLoginDto(KakaoLoginDto kakaoLoginDto) {
        this.nickname = kakaoLoginDto.getProperties().getNickname();
        this.snsId = kakaoLoginDto.getId();
        this.name = kakaoLoginDto.getProperties().getNickname();
        this.email = kakaoLoginDto.getKakaoAccount().getEmail();
        this.profile = kakaoLoginDto.getKakaoAccount().getProfile().getProfileImageUrl();
        this.snsType = SnsType.KAKAO_SNS_TYPE;
    }

    public LgLoginDto(NaverLoginDto naverLoginDto) {
        this.nickname = naverLoginDto.getNickname();
        this.snsId = naverLoginDto.getId();
        this.name = naverLoginDto.getName();
        this.email = naverLoginDto.getEmail();
        this.profile = naverLoginDto.getProfileImage();
        this.snsType = SnsType.NAVER_SNS_TYPE;
    }
}
