package com.kr.lg.module.main.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.db.entities.MainTrialTb;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 메인페이지 트라이얼 리스트 DTO
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainPostTrialDto implements MainPost {

    private Long id; // 관리자 식별자

    private String title; // 제목

    private String content; // 컨텐츠

    private String writer; // 작성자

    private String playVideo; // 비디오 URL

    private String writeDt; // 수정일

    private String profile; // 프로필

    private String thumbnail; // 썸네일

    public MainPostTrialDto(MainTrialTb mainTrialTb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.id = mainTrialTb.getTrialId();
        this.title = CommonUtils.subString(mainTrialTb.getTitle(), 15);
        this.content = CommonUtils.subString(mainTrialTb.getContent(), 30);
        this.writer = mainTrialTb.getWriter();
        this.writeDt = sdf.format(mainTrialTb.getWriteDt());
        this.playVideo = mainTrialTb.getPlayVideo();
        this.profile = mainTrialTb.getUserTb().getProfile();
        this.thumbnail = "https://i.pinimg.com/564x/ad/10/8f/ad108f8e82285778b9aa86adbbc4fc09.jpg"; // ToDo 썸네일 처리
    }

}
