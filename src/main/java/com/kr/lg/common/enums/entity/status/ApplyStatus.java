package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplyStatus implements EnumEntry {

     // 신청 상태 (0: 신청중, 1: 처리완료, 2: 취소)

    APPLY_STATUS(0),
    END_STATUS(1),
    CANCEL_STATUS(2)
    ;

    int code;

    public static ApplyStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
