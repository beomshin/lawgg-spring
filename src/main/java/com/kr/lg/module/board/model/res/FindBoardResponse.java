package com.kr.lg.module.board.model.res;

import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindBoardResponse extends AbstractSpec {

    BoardEntry board;
}
