package com.kr.lg.module.main.model.dto;

import com.kr.lg.common.utils.CommonUtils;
import com.kr.lg.db.entities.MainBoardTb;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;

/**
 * 메인 페이지 포지션 게시판 리스트 DTO
 */
@Getter
@Setter
@ToString
public class MainPostBoardDto implements MainPost {

    private Long id; // 식별자

    private String title; // 제목

    private String content; // 내용

    private String writer; // 작성자

    private String writeDt; // 수정일

    private Long view; // 조회수

    private Long recommendCount; // 추천수

    private int lineType; // 라인 타입 ( 0 : top , 1 : jug, 2 : mid, 3: add , 4: spt, 5: all)

    private String tag; // 게시판 태그

    public MainPostBoardDto(MainBoardTb mainBoardTb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        this.id = mainBoardTb.getBoardId();
        this.title = CommonUtils.subString(mainBoardTb.getTitle(), 15);
        this.content = CommonUtils.subString(mainBoardTb.getContent(), 30);
        this.writer = mainBoardTb.getWriter();
        this.writeDt = sdf.format(mainBoardTb.getWriteDt());
        this.view = mainBoardTb.getView();
        this.recommendCount = mainBoardTb.getRecommendCount();
        this.lineType = mainBoardTb.getLineType().getCode();
        this.tag = getTag(this.lineType);
    }
    
    private String getTag(int type) {
        switch (type) {
            case 0: return "탑";
            case 1: return "정글";
            case 2: return "미드";
            case 3: return "원딜";
            case 4: return "서폿";
        }
        return "기타";
    }


}
