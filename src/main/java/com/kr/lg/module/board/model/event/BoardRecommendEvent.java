package com.kr.lg.module.board.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardRecommendEvent { // BoardRecommendEvent

    private Long boardId;
    private int num;
}
