package com.kr.lg.model.web.net.request.user;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "회원 아이디 조회 요청 바디")
public class FindIURequest implements RootRequest { // FindIdUserRequest

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
