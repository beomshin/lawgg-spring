package com.kr.lg.module.trial.model.dto;

import com.kr.lg.db.entities.UserTb;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrialCreateCount { // TrialCountEvent

    private UserTb userTb;
    private Integer num;
}
