package com.kr.lg.module.thirdparty.model.req;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "다날 본인 인증 요청 Body")
public class CertificationsDanalRequest {

//    @ApiModelProperty(value = "에러 코드")
    private String error_code;

//    @ApiModelProperty(value = "에러 응답 메세지")
    private String error_msg;

//    @ApiModelProperty(value = "imp uid")
    private String imp_uid;

//    @ApiModelProperty(value = "상점 아이디")
    private String merchant_uid;

//    @ApiModelProperty(value = "pg provider")
    private String pg_provider;

//    @ApiModelProperty(value = "pg type")
    private String pg_type;

//    @ApiModelProperty(value = "성공 여부")
    private Boolean success;

}
