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
@ApiModel(value = "트라이얼 게시판 투표 요청 Body")
public class VoteTrialRequest {

    @ApiModelProperty(value = "트라이얼 아이디", required = true)
    @NotNull(message = "트라이얼 아이디가 입력되어있지않습니다.")
    private Long trialId;

    @ApiModelProperty(value = "트라이얼 투표 진영", required = true)
    @NotNull(message = "트라이얼 투표 진영이 입력되어있지않습니다.")
    private Integer precedent;

}
