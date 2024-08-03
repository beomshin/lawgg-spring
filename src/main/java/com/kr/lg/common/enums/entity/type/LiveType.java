package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LiveType implements EnumEntry {

    // 라이브 상태 ( 0: 미방송, 1: 방송)

    NON_LIVE_TYPE(0),
    LIVE_TYPE(1)
    ;

    int code;

    public static LiveType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
