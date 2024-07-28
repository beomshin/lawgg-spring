package com.kr.lg.module.user.model.req;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 프로필 변경하기 요청 아이디")
public class UpdateUPRequest implements RootRequest { // UpdateUserProfileRequest

    @ApiModelProperty(value = "파일", required = true)
    @NotBlank(message = "프로필 정보가 없습니다.")
    private MultipartFile profile;
}
