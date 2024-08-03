package com.kr.lg.common.enums.logic;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardTopic implements EnumEntry {

    NEW_TOPIC(0),
    HOT_TOPIC(1),
    ;

    int code;

    public static BoardTopic of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
