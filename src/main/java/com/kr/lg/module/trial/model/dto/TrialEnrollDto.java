
package com.kr.lg.module.trial.model.dto;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrialEnrollDto {

    private UserTb userTb; // 유저 엔티티
    private LawFirmTb lawFirmTb; // 로펌 엔티티
    private String password; // 패스워드
    private String title; // 제목
    private String content; // 내용
    private String subheading; // 소제목
    private String plaintiffOpinion; // 원고 의견
    private String defendantOpinion; // 피고 의견
    private String plaintiff; // 원고
    private String defendant; // 피고
    private String playVideo; // 영상

}
