package com.kr.lg.module.user.model.req;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 알림 수신 여부 업데이트 요청 바디")
public class UpdateUARequest implements RootRequest { // UpdateUserAlertRequest

    @ApiModelProperty(value = "알림 아이디", required = true)
    @NotBlank(message = "알림 아이디가 없습니다.")
    private Long id;
}
