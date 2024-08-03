package com.kr.lg.module.comment.model.dto;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEnrollDto {

    private UserTb userTb; // 유저 테이블
    private BoardTb boardTb; // 게시판 테이블
    private TrialTb trialTb; // 트라이얼 테이블
    private long parentId; // 부모 식별자
    private CommentDepthLevel depth; // 댓글 레벨
    private long id; // 게시판 식별자
    private Long boardCommentId; // 포지션 게시판 댓글 식별자
    private Long trialCommentId; // 트라이얼 댓글 식별자
    private String loginId; // 로그인 아이디
    private String password; // 패스워드
    private String writer; // 작성자
    private String content; // 내용
    private String emoticon; // 이모지
    private String ip; // ip

    public String getAlertTitle() {
        if (this.userTb == null) return "비회원 유저가 댓글을 달았습니다.";
        return new StringBuffer().append(this.userTb.getNickName()).append(" 유저가 댓글을 달았습니다.").toString();
    }
}
