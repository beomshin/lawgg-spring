package com.kr.lg.module.board.model.res;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindBoardResponse extends AbstractSpec {

    BoardEntry board;
}
