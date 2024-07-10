package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum implements LegacyEnum {

    ROOT_STATUS(0),
    NORMAL_STATUS(1),
    DELETE_STATUS(2),
    UPLOAD_STATUS(3),
    FAIL_STATUS(4),

    REPORT_STATUS(9)

    ;

    int code;

    public static StatusEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
