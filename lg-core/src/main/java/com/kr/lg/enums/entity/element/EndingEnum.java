package com.kr.lg.enums.entity.element;

import com.kr.lg.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndingEnum implements LegacyEnum {

    NON_ENDING_TYPE(0),
    ENDING_TYPE(1)
    ;

    int code;

    public static EndingEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
