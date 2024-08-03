package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationStatus implements EnumEntry {

    // 이메일 인증 상태 ( 0 : 미완료 , 1: 완료 )

    NON_COMPLETE(0),
    COMPLETE(1)
    ;

    int code;

    public static VerificationStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
