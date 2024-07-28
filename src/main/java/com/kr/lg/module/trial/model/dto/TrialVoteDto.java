package com.kr.lg.module.trial.model.dto;


import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.PrecedentEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrialVoteDto {


    private UserTb userTb;
    private TrialTb trialTb;
    private PrecedentEnum precedent;
    private Long trialVoteId;
}
