package com.kr.lg.model.net.request.board.base;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 포지션 게시판 삭제 요청 바디")
public class DeleteUBRequest implements RootRequest { // DeleteUserBoardRequest

    @ApiModelProperty(value = "게시판 아이디")
    @NotNull(message = "게시판 아이디가 누락 되었습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true)
    private String password;

}
