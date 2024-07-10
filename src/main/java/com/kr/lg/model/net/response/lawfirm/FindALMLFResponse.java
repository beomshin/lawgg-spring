package com.kr.lg.model.net.response.lawfirm;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindALMLFResponse extends DefaultResponse { // FindApplyListMyLawFirmResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;

    public FindALMLFResponse(Page<LawFirmQ> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
    }
}
