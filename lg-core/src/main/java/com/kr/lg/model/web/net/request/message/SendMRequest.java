
package com.kr.lg.model.web.net.request.message;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "메세지 발신 요청 바디")
public class SendMRequest implements RootRequest { // SendMessageRequest

    @ApiModelProperty(value = "받는이 아이디", required = true)
    @NotBlank(message = "받는이가 입력되어있지않습니다.")
    private String receiver;

    @ApiModelProperty(value = "제목", required = true)
    @NotBlank(message = "제목이 입력되어있지않습니다.")
    private String title;

    @ApiModelProperty(value = "내용", required = true)
    @NotBlank(message = "내용이 입력되어있지않습니다.")
    private String content;

}
