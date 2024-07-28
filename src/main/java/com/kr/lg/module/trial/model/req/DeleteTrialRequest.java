package com.kr.lg.module.trial.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "트라이얼 게시판 삭제 요청 Body")
public class DeleteTrialRequest {

    @ApiModelProperty(value = "트라이얼 아이디")
    @NotNull(message = "게시판아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드")
    @NotNull(message = "패스워드가 입력되어있지않습니다.")
    private String password;
}
