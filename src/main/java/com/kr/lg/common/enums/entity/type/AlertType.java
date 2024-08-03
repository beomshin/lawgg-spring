package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlertType implements EnumEntry {

    // 알림 타입 ( 0: 게시판 알림, 1: 트라이얼 알림)

    BOARD_ALERT_TYPE(0),
    TRIAL_ALERT_TYPE(1)
    ;

    int code;

    public static AlertType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
