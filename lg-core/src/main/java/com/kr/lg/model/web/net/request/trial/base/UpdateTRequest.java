package com.kr.lg.model.web.net.request.trial.base;

import com.kr.lg.model.web.common.root.RootRequest;
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
@ApiModel(value = "트라이얼 게시판 수정 요청 바디")
public class UpdateTRequest implements RootRequest { // UpdateTrialRequest

    @ApiModelProperty(value = "소제목", required = true)
    @NotNull(message = "소제목이 입력되어있지않습니다.")
    private String subheading;

    @ApiModelProperty(value = "원고의견", required = true)
    @NotNull(message = "원고의견이 입력되어있지않습니다.")
    private String plaintiffOpinion;

    @ApiModelProperty(value = "피고의견", required = true)
    @NotNull(message = "피고의견이 입력되어있지않습니다.")
    private String defendantOpinion;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "추가 파일")
    private List<MultipartFile> addFiles;

    @ApiModelProperty(value = "삭제 파일")
    private List<Long> deleteFiles;
}
