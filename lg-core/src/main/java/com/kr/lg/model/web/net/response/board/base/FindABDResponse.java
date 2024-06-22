package com.kr.lg.model.web.net.response.board.base;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.BoardQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindABDResponse extends DefaultResponse { // FindAnonymousBoardDetailResponse

    BoardQ board;
}
