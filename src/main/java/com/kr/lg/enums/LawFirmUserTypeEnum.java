package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmUserTypeEnum implements LegacyEnum {

    ALL_TYPE(0),
    NAME_TYPE(1)
    ;

    int code;

    public static LawFirmUserTypeEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
