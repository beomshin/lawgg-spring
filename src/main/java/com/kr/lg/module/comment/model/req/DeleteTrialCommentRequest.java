package com.kr.lg.module.comment.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "트라이얼 게시판 댓글 삭제 요청 Body")
public class DeleteTrialCommentRequest {

    @Schema(description = "게시판 아이디")
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long trialId;

    @Schema(description = "댓글 아이디")
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long commentId;

    @Schema(description = "패스워드")
    private String password;
}
