package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DelEnum implements LegacyEnum {

    NON_DELETE_STATUS(0),
    DELETE_STATUS(1)

    ;

    int code;

    public static DelEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
