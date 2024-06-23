package com.kr.lg.web.net.request.message;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "발신 메세지 리스트 조회 바디")
public class FindSMLRequest implements RootRequest { // FindSendMessageListRequest


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
