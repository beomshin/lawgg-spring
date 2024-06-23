package com.kr.lg.common.enums.entity.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AcceptEnum implements LegacyEnum {


    WAIT(0),
    ACCEPT(1),
    NON_ACCEPT(2)
    ;

    int code;

    public static AcceptEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
