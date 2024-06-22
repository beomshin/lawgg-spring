package com.kr.lg.model.web.net.request.lawfirm;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "로펌 수정 요청 바디")
public class UpdateLFRequest implements RootRequest { // UpdateLawFirmRequest

    @ApiModelProperty(value = "로펌 아이디", required = true)
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "소개글", required = true)
    @NotNull(message = "소개글이 입력되어있지않습니다.")
    private String introduction;

    @ApiModelProperty(value = "프로필 이미지")
    private MultipartFile profile;

    @ApiModelProperty(value = "배경 이미지")
    private MultipartFile background;

}
