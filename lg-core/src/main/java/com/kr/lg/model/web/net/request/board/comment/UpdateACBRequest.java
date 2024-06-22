package com.kr.lg.model.web.net.request.board.comment;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "포지션 게시판 익명 댓글 수정 요청 바디")
public class UpdateACBRequest implements RootRequest { // UpdateAnonymousCommentBoardRequest

    @ApiModelProperty(value = "댓글아이디", required = true)
    @NotNull(message = "댓글아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String password;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;
}
