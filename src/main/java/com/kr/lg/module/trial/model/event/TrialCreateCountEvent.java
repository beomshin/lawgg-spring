package com.kr.lg.module.trial.model.event;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrialCreateCountEvent {

    private UserTb userTb;
    private Integer num;
}
