package com.kr.lg.module.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardCommentCreateCountEvent { // BoardCommentEvent

    private Long boardId;
    private int num;
}
