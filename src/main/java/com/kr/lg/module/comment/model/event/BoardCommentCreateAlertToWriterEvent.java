package com.kr.lg.module.comment.model.event;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.module.comment.model.dto.CommentEnrollDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class BoardCommentCreateAlertToWriterEvent { // AlertBoardCommentEvent


    private Long boardId;
    private Long parentId;
    private String content;
    private String title;
    private UserTb userTb;


    public BoardCommentCreateAlertToWriterEvent(CommentEnrollDto commentEnrollDto) {
        this.boardId = commentEnrollDto.getId();
        if (commentEnrollDto.getDepth().equals(CommentDepthLevel.PARENT_COMMENT)) {
            this.parentId = commentEnrollDto.getParentId();
        } else if (commentEnrollDto.getDepth().equals(CommentDepthLevel.CHILDREN_COMMENT)) {
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
