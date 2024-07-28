package com.kr.lg.module.trial.model.dto;


import com.kr.lg.db.entities.TrialTb;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrialReportDto {

    private String ip;
    private String content;
    private TrialTb trialTb;

}
