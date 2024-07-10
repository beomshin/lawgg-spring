package com.kr.lg.model.net.request.trial.comment;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "트라이얼 게시판 댓글 삭제 요청 바디")
public class DeleteCTRequest implements RootRequest { // DeleteCommentTrialRequest

    @ApiModelProperty(value = "댓글 아이디", required = true)
    @NotNull(message = "댓글 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드", required = true)
    private String password;
}
