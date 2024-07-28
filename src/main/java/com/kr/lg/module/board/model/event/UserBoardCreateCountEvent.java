package com.kr.lg.module.board.model.event;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserBoardCreateCountEvent { // BoardCountEvent

    private UserTb userTb;
    private Integer num;
}
