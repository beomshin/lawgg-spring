package com.kr.lg.model.net.request.trial.base;

import com.kr.lg.web.dto.global.GlobalFile;
import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 트라이얼 게시판 등록 요청 바디")
public class EnrollUTRequest implements RootRequest { // EnrollUserTrialRequest

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "원고", required = true)
    @NotNull(message = "원고인이 입력되어있지않습니다.")
    private String plaintiff;

    @ApiModelProperty(value = "피고", required = true)
    @NotNull(message = "피고인이 입력되어있지않습니다.")
    private String defendant;

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

    @ApiModelProperty(value = "파일")
    private List<GlobalFile> files;

    @ApiModelProperty(value = "로펌게시판여부")
    private Integer isLawFirm;

}
