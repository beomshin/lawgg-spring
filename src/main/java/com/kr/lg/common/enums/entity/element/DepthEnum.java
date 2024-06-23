package com.kr.lg.common.enums.entity.element;

import com.kr.lg.common.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DepthEnum implements LegacyEnum {

    ROOT_COMMENT(0),
    PARENT_COMMENT(1),
    CHILDREN_COMMENT(2)
    ;

    int code;

    public static DepthEnum of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
