package com.kr.lg.common.enums.entity.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrecedentEnum implements LegacyEnum {

    PLAINTIFF(0),
    DEFENDANT(1),
    PROCEEDING(9),
    ;

    int code;

    public static PrecedentEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
