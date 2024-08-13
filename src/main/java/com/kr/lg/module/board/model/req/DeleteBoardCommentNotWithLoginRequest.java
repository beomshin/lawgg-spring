package com.kr.lg.module.board.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "비로그인 포지션 게시판 댓글 삭제 요청 Body")
public class DeleteBoardCommentNotWithLoginRequest {

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long boardId;


    @ApiModelProperty(value = "댓글 아이디", required = true)
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long commentId;

    @ApiModelProperty(value = "패스워드", required = true)
    private String password;
}
