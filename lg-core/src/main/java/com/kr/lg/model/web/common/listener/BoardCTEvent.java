package com.kr.lg.model.web.common.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class BoardCTEvent { // BoardCreateEvent


    private Long boardId;
    private String ip;
}
