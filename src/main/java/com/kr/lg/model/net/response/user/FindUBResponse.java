package com.kr.lg.model.net.response.user;

import com.kr.lg.web.common.root.DefaultResponse;
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
