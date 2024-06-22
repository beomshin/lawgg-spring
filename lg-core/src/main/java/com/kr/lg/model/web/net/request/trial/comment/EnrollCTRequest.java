package com.kr.lg.model.web.net.request.trial.comment;

import com.kr.lg.enums.entity.element.DepthEnum;
import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "트라이얼 게시판 댓글 등록 요청 바디")
public class EnrollCTRequest implements RootRequest { // EnrollCommentTrialRequest

    @ApiModelProperty(value = "트라이얼 아이디", required = true)
    @NotNull(message = "트라이얼 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "부모 댓글 아이디(트라이얼 댓글)")
    private Long parentId;

    @ApiModelProperty(value = "내용", required = true)
    @NotNull(message = "내용이 입력되어있지않습니다.")
    private String content;

    @ApiModelProperty(value = "이모티콘")
    private String emoticon;

    @ApiModelProperty(value = "댓글 레벨", required = true)
    @NotNull(message = "댓글 레벨이 입력되어있지않습니다.")
    private DepthEnum depth;

    @ApiModelProperty(value = "부모 댓글 아이디(대댓글)")
    private Long trialCommentId;

}
