package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplyStatusEnum implements LegacyEnum {

    APPLY_STATUS(0),
    END_STATUS(1),
    CANCEL_STATUS(2)
    ;

    int code;

    public static ApplyStatusEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
