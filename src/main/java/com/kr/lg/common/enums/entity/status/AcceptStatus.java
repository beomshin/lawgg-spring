package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AcceptStatus implements EnumEntry {

    // 로펌 신청 승인/거절 ( 0: 대기, 1: 승인, 2: 거절)

    WAIT(0),
    ACCEPT(1),
    NON_ACCEPT(2)
    ;

    final int code;

    public static AcceptStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
