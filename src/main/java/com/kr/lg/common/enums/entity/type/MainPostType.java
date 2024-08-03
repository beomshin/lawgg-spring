package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MainPostType implements EnumEntry {

    NORMAL_TYPE(0),
    MAIN_POST_TYPE(1),
    ;

    int code;

    public static MainPostType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
