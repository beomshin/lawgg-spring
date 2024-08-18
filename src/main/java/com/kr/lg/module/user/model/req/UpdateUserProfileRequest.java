package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 프로필 업데이트 요청 Body")
public class UpdateUserProfileRequest {

    @ApiModelProperty(value = "파일", required = true)
    @NotNull(message = "프로필 정보가 없습니다.")
    private MultipartFile profile;
}
