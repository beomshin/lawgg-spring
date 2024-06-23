package com.kr.lg.common.enums.entity.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VerificationEnum implements LegacyEnum {

    NON_COMPLETE(0),
    COMPLETE(1)
    ;

    int code;

    public static VerificationEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
