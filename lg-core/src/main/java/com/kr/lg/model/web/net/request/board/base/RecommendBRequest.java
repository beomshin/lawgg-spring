package com.kr.lg.model.web.net.request.board.base;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "포지션 게시판 추천 요청 바디")
public class RecommendBRequest implements RootRequest { // RecommendBoardRequestDto

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long id;
}
