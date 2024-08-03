package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardStatus implements EnumEntry {

    // 게시글 상태 ( 1: 정상, 2: 삭제, 9: 정지 )

    NORMAL_STATUS(1),
    DELETE_STATUS(2),

    REPORT_STATUS(9)

    ;

    int code;

    public static BoardStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
