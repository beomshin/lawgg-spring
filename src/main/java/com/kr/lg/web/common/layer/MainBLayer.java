package com.kr.lg.web.common.layer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.entities.MainBoardTb;
import com.kr.lg.enums.entity.element.LineEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class MainBLayer {

    private Long id; // 식별자
    private String title;
    private String content;
    private String writer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일
    private Long view;
    private Long recommendCount;
    private LineEnum lineType;

    public MainBLayer(MainBoardTb mainBoardTb) {
        this.id = mainBoardTb.getBoardId();
        this.title = mainBoardTb.getTitle();
        this.content = mainBoardTb.getContent();
        this.writer = mainBoardTb.getWriter();
        this.writeDt = mainBoardTb.getWriteDt();
        this.view = mainBoardTb.getView();
        this.recommendCount = mainBoardTb.getRecommendCount();
        this.lineType = mainBoardTb.getLineType();
    }


}
