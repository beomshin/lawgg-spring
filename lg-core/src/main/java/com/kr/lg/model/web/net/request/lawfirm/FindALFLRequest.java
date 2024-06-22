package com.kr.lg.model.web.net.request.lawfirm;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "전체 로펌 리스트 조회 요청 바디")
public class FindALFLRequest implements RootRequest { // FindAllLawFirmListRequest

    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @ApiModelProperty(value = "검색조건")
    private Integer subject;

    @ApiModelProperty(value = "키워드")
    private String keyword;

}
