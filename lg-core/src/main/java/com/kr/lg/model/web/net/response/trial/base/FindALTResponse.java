package com.kr.lg.model.web.net.response.trial.base;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.TrialQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindALTResponse extends DefaultResponse { // FindAllListTrialResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

    public FindALTResponse(Page<TrialQ> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
        this.curPage = list.getNumber();
    }

}
