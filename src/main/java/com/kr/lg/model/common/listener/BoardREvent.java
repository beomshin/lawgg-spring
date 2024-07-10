package com.kr.lg.model.common.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardREvent { // BoardRecommendEvent

    private Long boardId;
    private int num;
}
