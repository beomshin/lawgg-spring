package com.kr.lg.module.main.model.dto;

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

    private Long id; // 식별자

    private String title; // 제목

    private String content; // 내용

    private String playVideo; // 비디오 URL

    public HotPostTrialDto(TrialTb trialTb) {
        this.id = trialTb.getTrialId();
        this.title = trialTb.getTitle();
        this.content = trialTb.getContent();
        this.playVideo = trialTb.getPlayVideo();
    }
}
