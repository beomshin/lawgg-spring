package com.kr.lg.module.comment.model.event;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCommentCreateCountEvent { // CommentCountEvent

    private UserTb userTb;
    private Integer num;
}
