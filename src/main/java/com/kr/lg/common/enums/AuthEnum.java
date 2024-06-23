package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthEnum implements LegacyEnum {

    NON_AUTH_STATUS(0),
    AUTH_STATUS(1)

    ;

    int code;

    public static AuthEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
