package com.kr.lg.module.comment.model.dto;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.DepthEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class CommentCreateAlertToWriterEvent { // AlertBoardCommentEvent


    private Long boardId;
    private Long parentId;
    private String content;
    private String title;
    private UserTb userTb;


    public CommentCreateAlertToWriterEvent(CommentEnrollDto commentEnrollDto) {
        this.boardId = commentEnrollDto.getId();
        if (commentEnrollDto.getDepth().equals(DepthEnum.PARENT_COMMENT)) {
            this.parentId = commentEnrollDto.getParentId();
        } else if (commentEnrollDto.getDepth().equals(DepthEnum.CHILDREN_COMMENT)) {
            this.parentId = commentEnrollDto.getBoardCommentId();
        }
        this.content = commentEnrollDto.getContent();
        this.title = commentEnrollDto.getAlertTitle();
        this.userTb = commentEnrollDto.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
