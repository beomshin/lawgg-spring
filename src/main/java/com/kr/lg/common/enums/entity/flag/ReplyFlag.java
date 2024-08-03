package com.kr.lg.common.enums.entity.flag;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReplyFlag implements EnumEntry {

    NON_REPLY_FLAG(0),
    REPLY_FLAG(1)
    ;

    int code;

    public static ReplyFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
