package com.kr.lg.web.net.request.lawfirm;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "로펌 탈퇴 요청 바디")
public class QuitLFRequest implements RootRequest { // QuitLawFirmRequest

    @ApiModelProperty(value = "로펌 아이디", required = true)
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long id;
}
