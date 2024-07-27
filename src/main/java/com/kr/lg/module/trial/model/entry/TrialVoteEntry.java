package com.kr.lg.module.trial.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class TrialVoteEntry {

    private Integer plaintiffCount; // 원고 투표 퍼센트
    private Integer defendantCount; // 피고 투표 퍼센트


}
