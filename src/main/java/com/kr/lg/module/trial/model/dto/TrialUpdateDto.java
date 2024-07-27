
package com.kr.lg.module.trial.model.dto;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.PrecedentEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrialUpdateDto {

    private long trialId; // 트라이얼 식별자
    private UserTb userTb; // 유저 엔티티
    private String url; // 라이브 방송 URL
    private PrecedentEnum precedent; // 승자 플래그 0 원고, 1: 피고

}
