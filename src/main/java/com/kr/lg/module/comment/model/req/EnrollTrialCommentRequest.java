package com.kr.lg.module.comment.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "트라이얼 게시판 댓글 등록 요청 Body")
public class EnrollTrialCommentRequest {

    @Schema(description = "트라이얼 아이디")
    @NotNull(message = "트라이얼 아이디가 입력되어있지않습니다.")
    private Long trialId;

    @Schema(description = "내용")
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @Schema(description = "댓글 레벨")
    @NotNull(message = "댓글 레벨이 입력되어있지않습니다.")
    private int depth;


}
