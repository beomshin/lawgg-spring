package com.kr.lg.model.web.common.listener;

import com.kr.lg.entities.UserTb;
import com.kr.lg.enums.entity.element.DepthEnum;
import com.kr.lg.model.web.common.layer.BoardLayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class AlertBCEvent { // AlertBoardCommentEvent


    private Long boardId;
    private Long parentId;
    private String content;
    private String title;
    private UserTb userTb;


    public AlertBCEvent(BoardLayer boardLayer) {
        this.boardId = boardLayer.getId();
        if (boardLayer.getDepth().equals(DepthEnum.PARENT_COMMENT)) {
            this.parentId = boardLayer.getParentId();
        } else if (boardLayer.getDepth().equals(DepthEnum.CHILDREN_COMMENT)) {
            this.parentId = boardLayer.getBoardCommentId();
        }
        this.content = boardLayer.getContent();
        this.title = boardLayer.getAlertTitle();
        this.userTb = boardLayer.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
