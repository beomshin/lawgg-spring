package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttachStatus implements EnumEntry {

    // 첨부파일 상태 ( 1: 정상, 2: 삭제)

    NORMAL_STATUS(1),
    DELETE_STATUS(2),

    ;

    int code;

    public static AttachStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
