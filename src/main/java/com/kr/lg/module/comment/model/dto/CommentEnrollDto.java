package com.kr.lg.module.comment.model.dto;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.DepthEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentEnrollDto {

    private UserTb userTb;
    private BoardTb boardTb;
    private long parentId;
    private DepthEnum depth;
    private long id;
    private Long boardCommentId;
    private String loginId;
    private String password;
    private String writer;
    private String content;
    private String emoticon;
    private String ip;

    public String getAlertTitle() {
        if (this.userTb == null) return "비회원 유저가 댓글을 달았습니다.";
        return new StringBuffer().append(this.userTb.getNickName()).append(" 유저가 댓글을 달았습니다.").toString();
    }
}
