package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageDeleteStatus implements EnumEntry {

    // 수신자 | 발신자 삭제 플래그 (0: 미삭제, 1: 삭제)

    NON_DELETE_STATUS(0),
    DELETE_STATUS(1)

    ;

    int code;

    public static MessageDeleteStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
