package com.kr.lg.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialSubjectEnum implements LegacyEnum {

    ALL_TYPE(0),
    TITLE_TYPE(1),
    CONTENT_TYPE(2),
    WRITER_TYPE(3)
    ;

    int code;

    public static TrialSubjectEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
