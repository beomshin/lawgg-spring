package com.kr.lg.enums.entity.element;

import com.kr.lg.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DefenseEnum implements LegacyEnum {

    NORMAL(0),
    VICTORY(1),
    LOST(2)
    ;

    int code;

    public static DefenseEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
