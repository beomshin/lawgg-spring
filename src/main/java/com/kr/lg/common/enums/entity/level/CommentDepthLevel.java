package com.kr.lg.common.enums.entity.level;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentDepthLevel implements EnumEntry {

    // 댓글 레벨 ( 0 : 루트 , 1: 댓글, 2: 대댓글)

    ROOT_COMMENT(0),
    PARENT_COMMENT(1),
    CHILDREN_COMMENT(2)
    ;

    int code;

    public static CommentDepthLevel of(Integer code) {
        if (code == null) {
            return null;
        }

        return EnumEntry.of(values(), code);
    }
}
