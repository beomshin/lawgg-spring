package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LiveEnum implements LegacyEnum {

    NON_LIVE_TYPE(0),
    LIVE_TYPE(1)
    ;

    int code;

    public static LiveEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
