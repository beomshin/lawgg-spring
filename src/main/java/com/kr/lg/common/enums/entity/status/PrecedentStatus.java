package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrecedentStatus implements EnumEntry {

    // 판례 ( 0: 원고 승, 1: 피고 승, 9 :진행중)

    PLAINTIFF(0),
    DEFENDANT(1),
    PROCEEDING(9),
    ;

    int code;

    public static PrecedentStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
