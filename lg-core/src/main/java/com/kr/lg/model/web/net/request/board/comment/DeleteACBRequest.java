package com.kr.lg.model.web.net.request.board.comment;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "포지션 게시판 익명 댓글 삭제 요청 바디")
public class DeleteACBRequest implements RootRequest { // DeleteAnonymousCommentBoardRequest

    @ApiModelProperty(value = "댓글 아이디", required = true)
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotNull(message = "패스워드가 입력되어있지않습니다.")
    private String password;
}
