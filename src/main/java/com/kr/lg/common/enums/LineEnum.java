package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LineEnum implements LegacyEnum {

    TOP(0),
    JUNGLE(1),
    MID(2),
    ADD(3),
    SPT(4),
    ALL(5)
    ;

    int code;

    public static LineEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
