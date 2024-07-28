package com.kr.lg.module.comment.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrialCommentCreateCountEvent { // TrialCommentEvent

    private Long trialId;
    private int num;
}
