package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DefenseStatus implements EnumEntry {

    // 로펌 승리 ( 0: 일반 상태, 1: 승소, 2: 패소)

    NORMAL(0),
    VICTORY(1),
    LOST(2)
    ;

    int code;

    public static DefenseStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
