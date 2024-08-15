package com.kr.lg.module.main.model.dto;

import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.db.entities.TrialTb;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상단 HOT 트라이얼 정보 DTO
 */
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotPostTrialDto implements MainPost {

    private Long trialId; // 식별자

    private String title; // 제목

    private String content; // 내용

    private String playVideo; // 비디오 URL

    public HotPostTrialDto(TrialTb trialTb) {
        this.trialId = trialTb.getTrialId();
        this.title = CommonUtils.subString(trialTb.getTitle(), 15);
        this.content = CommonUtils.subString(trialTb.getContent(), 30);
        this.playVideo = trialTb.getPlayVideo();
    }
}
