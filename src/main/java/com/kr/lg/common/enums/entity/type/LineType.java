package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LineType implements EnumEntry {

    // 라인 타입 ( 0 : top , 1 : jug, 2 : mid, 3: add , 4: spt, 5: all)

    TOP(0),
    JUNGLE(1),
    MID(2),
    ADD(3),
    SPT(4),
    ALL(5)
    ;

    int code;

    public static LineType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
