package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status3Enum implements LegacyEnum {

    NORMAL_STATUS(1),
    REPORT_STATUS(2),

    DELETE_STATUS(9)

    ;

    int code;

    public static Status3Enum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
