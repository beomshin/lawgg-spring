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
@ApiModel(value = "비로그인 포지션 게시판 삭제 요청 Body")
public class DeleteBoardWithNotLoginRequest {

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 누락 되었습니다.")
    private Long id;

    @ApiModelProperty(value = "게시판 비밀번호", required = true)
    @NotNull(message = "게시판 비밀번호가 누락 되었습니다.")
    private String password;

}
