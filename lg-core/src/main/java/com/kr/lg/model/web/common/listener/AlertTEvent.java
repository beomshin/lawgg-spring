package com.kr.lg.model.web.common.listener;

import com.kr.lg.entities.UserTb;
import com.kr.lg.model.web.common.layer.TrialLayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class AlertTEvent { // AlertTrialEvent

    private Long trialId;
    private String content;
    private String title;
    private UserTb userTb;

    public AlertTEvent(TrialLayer requestDto) {
        this.trialId = requestDto.getId();
        this.content = requestDto.getContent();
        this.title = requestDto.getAlertTitle();
        this.userTb = requestDto.getUserTb();
    }

    public boolean isPost(UserTb writer) {
        return this.userTb == null || !writer.getUserId().equals(userTb.getUserId());
    }
}
