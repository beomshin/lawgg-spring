package com.kr.lg.model.net.response.board.base;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUBDResponse extends DefaultResponse {  // FindUserBoardDetailResponse

    BoardQ board;
}
