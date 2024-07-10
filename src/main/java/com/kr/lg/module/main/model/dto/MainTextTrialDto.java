package com.kr.lg.module.main.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.db.entities.MainTrialTb;
import lombok.*;

import java.sql.Timestamp;

/**
 * 메인페이지 트라이얼 리스트 DTO
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainTextTrialDto implements MainText {

    private Long id; // 관리자 식별자

    private String title; // 제목

    private String content; // 컨텐츠

    private String writer; // 작성자

    private String playVideo; // 비디오 URL

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    public MainTextTrialDto(MainTrialTb mainTrialTb) {
        this.id = mainTrialTb.getTrialId();
        this.title = mainTrialTb.getTitle();
        this.content = mainTrialTb.getContent();
        this.writer = mainTrialTb.getWriter();
        this.writeDt = mainTrialTb.getWriteDt();
        this.playVideo = mainTrialTb.getPlayVideo();
    }

}
