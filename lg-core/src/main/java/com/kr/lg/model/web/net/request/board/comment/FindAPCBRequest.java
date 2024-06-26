package com.kr.lg.model.web.net.request.board.comment;

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
@ApiModel(value = "포지션 게시판 익명 부모 댓글 조회")
public class FindAPCBRequest implements RootRequest { // FindAnonymousParentCommentBoardRequest

    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;
}
