package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WriterType implements EnumEntry {

    // 작성 타입 ( 0: 비회원, 1: 회원, 2: 로펌 )

    ANONYMOUS_TYPE(0),
    MEMBER_TYPE(1),
    LAW_FIRM_TYPE(2)
    ;

    int code;

    public static WriterType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }

}
