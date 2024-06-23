package com.kr.lg.web.net.request.common;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "파일 리스트 등록 요청 바디")
public class FileRequest implements RootRequest {

    @ApiModelProperty(value = "파일 리스트", required = true)
    @NotNull(message = "파일 리스트가 존재하지 않습니다.")
    List<MultipartFile> files;
}
