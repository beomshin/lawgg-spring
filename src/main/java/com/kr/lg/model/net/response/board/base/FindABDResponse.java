package com.kr.lg.model.net.response.board.base;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindABDResponse extends DefaultResponse { // FindAnonymousBoardDetailResponse

    BoardQ board;
}
