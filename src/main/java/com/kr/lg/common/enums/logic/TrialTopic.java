package com.kr.lg.common.enums.logic;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrialTopic implements EnumEntry {

    ALL_TOPIC(0),
    LIVE_TOPIC(1),
    ;

    int code;

    public static TrialTopic of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
