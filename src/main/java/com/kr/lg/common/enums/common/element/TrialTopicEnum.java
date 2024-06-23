package com.kr.lg.common.enums.common.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrialTopicEnum implements LegacyEnum {

    ALL_TOPIC(0),
    LIVE_TOPIC(1),
    ;

    int code;

    public static TrialTopicEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
