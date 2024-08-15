package com.kr.lg.module.main.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.db.entities.MainTrialTb;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 메인페이지 트라이얼 리스트 DTO
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainPostTrialDto implements MainPost {

    private Long trialId; // 관리자 식별자

    private String title; // 제목

    private String content; // 컨텐츠

    private String writer; // 작성자

    private String playVideo; // 비디오 URL

    private String writeDt; // 수정일

    private String profile; // 프로필

    private String thumbnail; // 썸네일

    public MainPostTrialDto(MainTrialTb mainTrialTb) {
        List<String> list = Arrays.asList("https://i.pinimg.com/564x/ad/10/8f/ad108f8e82285778b9aa86adbbc4fc09.jpg",
                "https://i.pinimg.com/originals/bc/3d/fa/bc3dfa536dcbe996f7fe27db6edd88b9.gif",
                "https://i.pinimg.com/originals/ba/12/28/ba12284310ac3bb48213f43577092310.gif",
                "https://i.pinimg.com/564x/53/7f/ee/537fee1351057a3b3e77b9b7457920b4.jpg");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.trialId = mainTrialTb.getTrialId();
        this.title = CommonUtils.subString(mainTrialTb.getTitle(), 15);
        this.content = CommonUtils.subString(mainTrialTb.getContent(), 30);
        this.writer = mainTrialTb.getWriter();
        this.writeDt = sdf.format(mainTrialTb.getWriteDt());
        this.playVideo = mainTrialTb.getPlayVideo();
        this.profile = mainTrialTb.getUserTb().getProfile();


        this.thumbnail = list.get((int) (this.trialId%4)); // ToDo 썸네일 처리
    }

}
