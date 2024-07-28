package com.kr.lg.module.board.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class BoardCountEvent { // BoardCreateEvent


    private Long boardId;
    private String ip;
}
