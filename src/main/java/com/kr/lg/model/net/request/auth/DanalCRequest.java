package com.kr.lg.model.net.request.auth;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "다날 본인 인증 요청 바디")
public class DanalCRequest implements RootRequest { // CertificationsDanalRequest

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
