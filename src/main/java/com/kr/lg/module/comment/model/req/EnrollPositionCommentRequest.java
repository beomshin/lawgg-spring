package com.kr.lg.module.comment.model.req;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "비로그인 포지션 게시판 댓글 등록 요청 Body")
public class EnrollPositionCommentRequest {

//    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long boardId;

//    @ApiModelProperty(value = "등록 아이디", required = true)
    @NotBlank(message = "아이디 값이 입력되어있지않습니다.")
    private String loginId;

//    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String password;

//    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

//    @ApiModelProperty(value = "댓글 레벨", required = true)
    @NotNull(message = "댓글 레벨이 입력되어있지않습니다.")
    private int depth;

}
