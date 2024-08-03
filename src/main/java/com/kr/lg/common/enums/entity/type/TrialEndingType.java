package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialEndingType implements EnumEntry {

    // 종료 재판 ( 0: 미종료, 1: 종료)

    NON_ENDING_TYPE(0),
    ENDING_TYPE(1)
    ;

    int code;

    public static TrialEndingType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
