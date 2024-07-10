package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SnsEnum implements LegacyEnum {

    LG_SNS_TYPE(0),
    GOOGLE_SNS_TYPE(1),
    NAVER_SNS_TYPE(2),
    KAKAO_SNS_TYPE(3)
    ;

    int code;

    public static SnsEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
