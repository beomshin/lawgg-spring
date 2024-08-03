package com.kr.lg.common.enums.entity.flag;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JudgeUserFlag implements EnumEntry {

    // 재판 가능 유저 플래그 ( 0: 미가능, 1: 가능)

    NON_USE_STATUS(0),
    USE_STATUS(1)
    ;

    int code;

    public static JudgeUserFlag of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
