package com.kr.lg.module.user.model.req;


import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "아이디 중복 체크 요청 Body")
public class CheckIdRequest {

//    @ApiModelProperty(value = "로그인 아이디", required = true)
    @NotBlank(message = "로그인 아이디가 누락되었습니다.")
    private String loginId;


}
