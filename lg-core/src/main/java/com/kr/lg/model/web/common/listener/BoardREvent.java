package com.kr.lg.model.web.common.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardREvent { // BoardRecommendEvent

    private Long boardId;
    private int num;
}
