package com.kr.lg.model.net.request.trial.base;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "트라이얼 게시판 신고 요청 바디")
public class ReportTRequest implements RootRequest { // ReportTrialRequest

    @ApiModelProperty(value = "트라이얼 아이디", required = true)
    @NotNull(message = "트라이얼 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "내용", required = true)
    private String content;
}