package com.kr.lg.module.trial.model.req;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "트라이얼 게시판 재판 종료 요청 Body")
public class EndTrialRequest {

//    @ApiModelProperty(value = "트라이얼 아이디", required = true)
    @NotNull(message = "트라이얼 아이디가 입력되어있지않습니다.")
    private Long trialId;

}
