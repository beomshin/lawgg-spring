package com.kr.lg.module.comment.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentUpdateDto {

    private long boardCommentId; // 댓글 식별자
    private String content; // 내용

}
