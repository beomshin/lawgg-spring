package com.kr.lg.web.net.request.board.base;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "익명 포지션 게시판 삭제 요청 바디")
public class DeleteABRequest implements RootRequest { // DeleteAnonymousBoardRequest

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 누락 되었습니다.")
    private Long id;

    @ApiModelProperty(value = "게시판 비밀번호", required = true)
    @NotNull(message = "게시판 비밀번호가 누락 되었습니다.")
    private String password;

}
