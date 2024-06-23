package com.kr.lg.web.net.response.user;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.AlertQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUAResponse extends DefaultResponse { // FindUserAlertResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

    public FindUAResponse(Page<AlertQ> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
        this.curPage = list.getNumber();
    }

}
