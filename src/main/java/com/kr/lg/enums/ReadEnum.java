package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReadEnum implements LegacyEnum {

    NON_READ_FLAG(0),
    READ_FLAG(1)
    ;

    int code;

    public static ReadEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
