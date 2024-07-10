package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmTypeEnum implements LegacyEnum {

    ALL_TYPE(0),
    LAW_FIRM_NAME_TYPE(1),
    REP_NAME_TYPE(2),

    ;

    int code;

    public static LawFirmTypeEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
