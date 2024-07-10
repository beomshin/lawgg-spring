package com.kr.lg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardTopicEnum implements LegacyEnum {

    NEW_TOPIC(0),
    HOT_TOPIC(1),
    ;

    int code;

    public static BoardTopicEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
