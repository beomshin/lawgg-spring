package com.kr.lg.model.web.net.request.message;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "수신 메세지 리스트 조회 바디")
public class FindRMLRequest implements RootRequest { // FindReceiveMessageListRequest


    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    private Integer subject;

    private String keyword;
}
