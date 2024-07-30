package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 알림 읽기 업데이트 요청 Body")
public class UpdateUserAlertRequest {

    @ApiModelProperty(value = "알림 아이디", required = true)
    @NotNull(message = "알림 아이디가 없습니다.")
    private Long id;
}
