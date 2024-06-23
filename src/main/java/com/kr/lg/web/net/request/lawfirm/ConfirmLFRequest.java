package com.kr.lg.web.net.request.lawfirm;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "로펌 가입 요청 바디")
public class ConfirmLFRequest implements RootRequest { // ConfirmLawFirmRequest

    @ApiModelProperty(value = "로펌 신청 아이디", required = true)
    @NotNull(message = "로펌 신청 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "로펌 신청 아이디", required = true)
    @NotNull(message = "로펌 신청 아이디가 입력되어있지않습니다.")
    private Integer accept; // 0: 미수용, 1: 수용



}
