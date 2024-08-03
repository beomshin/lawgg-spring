package com.kr.lg.module.comment.model.event;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.module.comment.model.dto.CommentEnrollDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class TrialCommentCreateAlertToWriterEvent {

    private Long trialId;
    private Long parentId;
    private String content;
    private String title;
    private UserTb userTb;

    public TrialCommentCreateAlertToWriterEvent(CommentEnrollDto requestDto) {
        this.trialId = requestDto.getId();
        this.content = requestDto.getContent();
        if (requestDto.getDepth().equals(CommentDepthLevel.PARENT_COMMENT)) {
            this.parentId = requestDto.getParentId();
        } else if (requestDto.getDepth().equals(CommentDepthLevel.CHILDREN_COMMENT)){
            this.parentId = requestDto.getTrialCommentId();
        }
        this.title = requestDto.getAlertTitle();
        this.userTb = requestDto.getUserTb();
    }


    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
