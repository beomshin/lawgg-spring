package com.kr.lg.module.board.model.req;

import com.kr.lg.web.dto.root.AbstractSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "포지션 게시판 신고 Body")
public class ReportBoardRequest extends AbstractSpec {

    @ApiModelProperty(value = "게시판 아이디", required = true)
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long id;

    private String content;
}