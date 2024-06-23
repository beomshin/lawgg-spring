package com.kr.lg.enums.entity.element;

import com.kr.lg.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlertEnum implements LegacyEnum {


    BOARD_ALERT_TYPE(0),
    TRIAL_ALERT_TYPE(1)
    ;

    int code;

    public static AlertEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
