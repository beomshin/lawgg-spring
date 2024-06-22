package com.kr.lg.enums.entity.element;

import com.kr.lg.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status2Enum implements LegacyEnum {

    STOP_STATUS(0),
    NORMAL_STATUS(1),
    DELETE_STATUS(2)

    ;

    int code;

    public static Status2Enum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
