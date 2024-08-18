package com.kr.lg.common.enums.logic;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LawFirmTopic implements EnumEntry {

    NEW_TOPIC(0),
    HOT_TOPIC(1),
    VIEW_TOPIC(2),

    ;

    int code;

    public static LawFirmTopic of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
