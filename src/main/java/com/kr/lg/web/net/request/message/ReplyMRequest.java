package com.kr.lg.web.net.request.message;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "메세지 수신 요청 바디")
public class ReplyMRequest implements RootRequest { // ReplyMessageRequest

    @ApiModelProperty(value = "수신 메세지 아이디", required = true)
    @NotBlank(message = "수신 메세지 아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "title", required = true)
    @NotBlank(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotBlank(message = "내용이 입력되어있지않습니다.")
    private String content;

}
