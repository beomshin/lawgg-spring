package com.kr.lg.module.main.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.db.entities.MainBoardTb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 메인 페이지 포지션 게시판 리스트 DTO
 */
@Getter
@Setter
@ToString
public class MainTextBoardDto implements MainText {

    private Long id; // 식별자

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    private Long view; // 조회수

    private Long recommendCount; // 추천수

    private int lineType; // 라인 타입

    public MainTextBoardDto(MainBoardTb mainBoardTb) {
        this.id = mainBoardTb.getBoardId();
        this.title = mainBoardTb.getTitle();
        this.content = mainBoardTb.getContent();
        this.writer = mainBoardTb.getWriter();
        this.writeDt = mainBoardTb.getWriteDt();
        this.view = mainBoardTb.getView();
        this.recommendCount = mainBoardTb.getRecommendCount();
        this.lineType = mainBoardTb.getLineType().getCode();
    }


}
