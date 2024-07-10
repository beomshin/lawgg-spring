package com.kr.lg.model.net.request.lawfirm;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "로펌 신청 요청 바디")
public class ApplyLFRequest implements RootRequest { // ApplyLawFirmRequest

    @ApiModelProperty(value = "로펌 아이디", required = true)
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "가입 신청글", required = true)
    @NotNull(message = "가입 신청글이 입력되어있지않습니다.")
    private String introduction;

}
