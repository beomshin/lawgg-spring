package com.kr.lg.module.comment.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로그인 포지션 게시판 댓글 등록 요청 Body")
public class EnrollBoardCommentWithLoginRequest  {

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "부모 댓글 아이디(게시판 댓글)")
    private Long parentId;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "이모티콘")
    private String emoticon;

    @ApiModelProperty(value = "댓글 레벨", required = true)
    @NotNull(message = "댓글 레벨이 입력되어있지않습니다.")
    private int depth;

    @ApiModelProperty(value = "부모 댓글 아이디(대댓글)")
    private Long boardCommentId;

}
