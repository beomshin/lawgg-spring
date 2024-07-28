package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 아이디 조회 요청 Body")
public class FindUserIdRequest {

    @ApiModelProperty(value = "에러 코드")
    private String error_code;

    @ApiModelProperty(value = "에러 응답 메세지")
    private String error_msg;

    @ApiModelProperty(value = "imp uid")
    private String imp_uid;

    @ApiModelProperty(value = "상점 아이디")
    private String merchant_uid;

    @ApiModelProperty(value = "pg provider")
    private String pg_provider;

    @ApiModelProperty(value = "pg type")
    private String pg_type;

    @ApiModelProperty(value = "성공 여부")
    private Boolean success;
}
