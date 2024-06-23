package com.kr.lg.common.enums.entity.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WriterEnum implements LegacyEnum {

    ANONYMOUS_TYPE(0),
    MEMBER_TYPE(1),
    LAW_FIRM_TYPE(2)
    ;

    int code;

    public static WriterEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }

}
