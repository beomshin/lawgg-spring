package com.kr.lg.web.net.request.user;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 게시판 리스트 조회 요청 바디")
public class FindUBRequest implements RootRequest { // FindUserBoardsRequest

    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;


    @ApiModelProperty(value = "키워드")
    private String keyword;

}
