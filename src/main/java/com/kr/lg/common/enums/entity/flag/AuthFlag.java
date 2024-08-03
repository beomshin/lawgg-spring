package com.kr.lg.common.enums.entity.flag;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthFlag implements EnumEntry {

    // 인증 플래그 ( 0: 미인증, 1: 인증)

    NON_AUTH_STATUS(0),
    AUTH_STATUS(1)

    ;

    int code;

    public static AuthFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
