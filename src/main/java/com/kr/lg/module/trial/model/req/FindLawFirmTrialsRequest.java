package com.kr.lg.module.trial.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로펌 트라이얼 게시판 리스트 조회 요청 Body")
public class FindLawFirmTrialsRequest {

    @ApiModelProperty(value = "로펌 아이디", required = true)
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @ApiModelProperty(value = "토픽", required = true)
    @NotNull(message = "토픽이 입력되어있지않습니다.")
    private Integer topic;

    @ApiModelProperty(value = "검색 조건")
    private Integer subject;

    @ApiModelProperty(value = "키워드")
    private String keyword;

}
