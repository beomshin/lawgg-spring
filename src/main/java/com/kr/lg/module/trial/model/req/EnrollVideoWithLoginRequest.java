package com.kr.lg.module.trial.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로그인 트라이얼 비디오,리플레이 등록 요청 Body")
public class EnrollVideoWithLoginRequest {

    @NotNull
    @ApiModelProperty(value = "비디오")
    private MultipartFile playVideo;

    @ApiModelProperty(value = "리플레이")
    private MultipartFile replay;

    @ApiModelProperty(value = "트라이얼 아이디")
    private Long id;

}
