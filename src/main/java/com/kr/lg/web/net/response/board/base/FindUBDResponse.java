package com.kr.lg.web.net.response.board.base;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.BoardQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUBDResponse extends DefaultResponse {  // FindUserBoardDetailResponse

    BoardQ board;
}
