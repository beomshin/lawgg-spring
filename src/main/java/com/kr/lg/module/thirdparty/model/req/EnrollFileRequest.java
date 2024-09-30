package com.kr.lg.module.thirdparty.model.req;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "파일 리스트 등록 요청 Body")
public class EnrollFileRequest {

//    @ApiModelProperty(value = "파일 리스트", required = true)
    @NotNull(message = "파일 리스트가 존재하지 않습니다.")
    List<MultipartFile> files;
}
