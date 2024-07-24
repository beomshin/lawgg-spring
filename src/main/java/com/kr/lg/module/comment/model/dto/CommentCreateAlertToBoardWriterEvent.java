package com.kr.lg.module.comment.model.dto;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class CommentCreateAlertToBoardWriterEvent { // AlertBoardEvent

    private Long boardId;
    private String content;
    private String title;
    private UserTb userTb;

    public CommentCreateAlertToBoardWriterEvent(CommentEnrollDto commentEnrollDto) {
        this.boardId = commentEnrollDto.getId();
        this.content = commentEnrollDto.getContent();
        this.title = commentEnrollDto.getAlertTitle();
        this.userTb = commentEnrollDto.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
