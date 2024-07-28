package com.kr.lg.module.user.model.res;

import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUBResponse extends DefaultResponse { // FindUserBoardsResponse

    private List list;
    private Integer totalElements;
    private Integer totalPage;
    private Integer curPage;
}
