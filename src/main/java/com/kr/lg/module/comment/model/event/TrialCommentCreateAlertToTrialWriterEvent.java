package com.kr.lg.module.comment.model.event;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.dto.CommentEnrollDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class TrialCommentCreateAlertToTrialWriterEvent {

    private Long trialId;
    private String content;
    private String title;
    private UserTb userTb;


    public TrialCommentCreateAlertToTrialWriterEvent(CommentEnrollDto enrollDto) {
        this.trialId = enrollDto.getId();
        this.content = enrollDto.getContent();
        this.title = enrollDto.getAlertTitle();
        this.userTb = enrollDto.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
