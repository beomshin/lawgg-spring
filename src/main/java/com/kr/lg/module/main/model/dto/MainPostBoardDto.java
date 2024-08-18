package com.kr.lg.module.main.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.utils.CommonUtils;
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
public class MainPostBoardDto {

    private Long boardId; // 식별자

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 작성일

    private Long view; // 조회수

    private Long recommendCount; // 추천수

    private int lineType; // 라인 타입 ( 0 : top , 1 : jug, 2 : mid, 3: add , 4: spt, 5: all)

    private String tag; // 게시판 태그

    public MainPostBoardDto(MainBoardTb mainBoardTb) {
        this.boardId = mainBoardTb.getBoardId();
        this.title = CommonUtils.subString(mainBoardTb.getTitle(), 15);
        this.content = CommonUtils.subString(mainBoardTb.getContent(), 30);
        this.writer = mainBoardTb.getWriter();
        this.writeDt = mainBoardTb.getWriteDt();
        this.view = mainBoardTb.getView();
        this.recommendCount = mainBoardTb.getRecommendCount();
        this.lineType = mainBoardTb.getLineType().getCode();
        this.tag = CommonUtils.getLineTag(this.lineType);
    }


}
