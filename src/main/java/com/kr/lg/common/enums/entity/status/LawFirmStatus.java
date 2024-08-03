package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmStatus implements EnumEntry {

    // 로펌 상태( 0: 미운영, 1: 운영, 2: 삭제 )

    STOP_STATUS(0),
    NORMAL_STATUS(1),
    DELETE_STATUS(2)

    ;

    int code;

    public static LawFirmStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
