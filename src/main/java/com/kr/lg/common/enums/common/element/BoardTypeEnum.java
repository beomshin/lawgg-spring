package com.kr.lg.common.enums.common.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardTypeEnum implements LegacyEnum {

    ALL_TYPE(0),
    TITLE_TYPE(1),
    CONTENT_TYPE(2),
    WRITER_TYPE(3)
    ;

    int code;

    public static BoardTypeEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
