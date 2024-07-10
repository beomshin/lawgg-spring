package com.kr.lg.model.common.listener;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.common.layer.BoardLayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class AlertBEvent { // AlertBoardEvent

    private Long boardId;
    private String content;
    private String title;
    private UserTb userTb;

    public AlertBEvent(BoardLayer boardLayer) {
        this.boardId = boardLayer.getId();
        this.content = boardLayer.getContent();
        this.title = boardLayer.getAlertTitle();
        this.userTb = boardLayer.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
