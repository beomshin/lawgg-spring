package com.kr.lg.model.common.layer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.db.entities.MainTrialTb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class MainTLayer {

    private Long id; // 관리자 식별자
    private String title;
    private String content;
    private String writer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일
    private String playVideo;

    public MainTLayer(MainTrialTb mainTrialTb) {
        this.id = mainTrialTb.getTrialId();
        this.title = mainTrialTb.getTitle();
        this.content = mainTrialTb.getContent();
        this.writer = mainTrialTb.getWriter();
        this.writeDt = mainTrialTb.getWriteDt();
        this.playVideo = mainTrialTb.getPlayVideo();
    }

}
