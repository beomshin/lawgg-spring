package com.kr.lg.module.lawfirm.model.req;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "로펌 신청 요청 Body")
public class ApplyLawFirmRequest {

//    @ApiModelProperty(value = "로펌 아이디", required = true)
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long lawfirmId;

}
