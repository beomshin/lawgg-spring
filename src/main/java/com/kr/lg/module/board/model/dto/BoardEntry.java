package com.kr.lg.module.board.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardEntry {

    private long boardId; // board 식별자
    private int postType; // 게시글 타입
    private String title; // 제목
    private String writer; // 작성자
    private Timestamp writeDt; // 작성일
    private int view; // 조회수
    private int recommendCount; // 추천수
    private int commentCount; // 댓글수
    private int writerType; // 작성자 타입
    private int lineType; // 라인타입
    private String profile; // 프로필 url
}
