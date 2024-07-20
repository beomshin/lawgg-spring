package com.kr.lg.common.enums.entity.status;

import com.kr.lg.enums.LegacyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus implements LegacyEnum {

    NORMAL(1), // 정상 상태
    REPORT(2), // 정지 상태
    DELETE(9) // 삭제 상태

    ;

    int code;

    public static UserStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return LegacyEnum.of(values(), code);
    }
}
