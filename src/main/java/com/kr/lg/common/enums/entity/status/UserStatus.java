package com.kr.lg.common.enums.entity.status;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus implements EnumEntry {

    // 유저 상태 ( 1: 정상, 2: 정지, 9: 탈퇴)

    NORMAL(1), // 정상 상태
    REPORT(2), // 정지 상태
    DELETE(9) // 삭제 상태

    ;

    int code;

    public static UserStatus of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
