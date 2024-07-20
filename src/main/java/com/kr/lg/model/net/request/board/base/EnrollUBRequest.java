package com.kr.lg.model.net.request.board.base;

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
@ApiModel(value = "회원 포지션 게시판 등록 요청 바디")
public class EnrollUBRequest implements RootRequest { // EnrollUserBoardRequest

    @ApiModelProperty(value = "제목", required = true)
    @NotNull(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "라인타입", required = true)
    @NotNull(message = "라인타입이 입력되어있지않습니다.")
    private Integer lineType;

    @ApiModelProperty(value = "파일")
    private List<GlobalFile> files;

    @ApiModelProperty(value = "로펌게시판여부")
    private Integer isLawFirm;
}
