package com.kr.lg.web.common.listener;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommnetCNTEvent { // CommentCountEvent

    private UserTb userTb;
    private Integer num;
}
