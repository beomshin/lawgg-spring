package com.kr.lg.module.user.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class UserBoardEntry {

    private long id; // 게시판 식별자
    private long userId; // 유저 아이디
    private long lawFirmId; // 로펌 아이디
    private String title; // 제목
    private String writer; // 작성자
    private String content; // 게시판 내용
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp writeDt; // 작성일
    private int postType; // 게시글 타입
    private int recommendCount; // 추천수
    private int commentCount; // 댓글수
    private int view; // 조회수
    private int report; // 신고수
    private int type; // 게시글 타입 (0: 포지션 게시판, 1: 트라이얼)
    private int status; // 상태
}
