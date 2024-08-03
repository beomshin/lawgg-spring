package com.kr.lg.common.enums.entity.flag;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReadFlag implements EnumEntry {

    NON_READ_FLAG(0),
    READ_FLAG(1)
    ;

    int code;

    public static ReadFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
