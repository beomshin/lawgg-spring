package com.kr.lg.common.enums.entity.type;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostType implements EnumEntry {

    // 게시글 타입 ( 0 : 일반, 1: 이미지, 2: 추천, 3: 베스트, 98: 이벤트, 99: 공지 )

    NORMAL_TYPE(0),
    IMAGE_TYPE(1),
    RECOMMEND(2),
    BEST_TYPE(3),
    EVENT_TYPE(98),
    NOTICE_TYPE(99),
    ;

    int code;

    public static PostType of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
