package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReplyEnum implements LegacyEnum {

    NON_REPLY_FLAG(0),
    REPLY_FLAG(1)
    ;

    int code;

    public static ReplyEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
