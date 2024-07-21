package com.kr.lg.module.board.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCommentEntry {

    private long boardCommentId; // boardComment 식별자
    private long parentId; // 댓글 부모 키
    private int order; // 댓글 순번
    private int depth; // 댓글 레벨 ( 0 : 루트 , 1: 댓글, 2: 대댓글)
    private String writer; // 작성자
    private String content; // 내용
    private String status; // 상태
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    private int created; // 작성 플래그
    private int anonymous; // 익명 플래그
    private String ip; // ip
    private String profile; // 프로필

}
