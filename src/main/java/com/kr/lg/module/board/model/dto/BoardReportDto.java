package com.kr.lg.module.board.model.dto;


import com.kr.lg.db.entities.BoardTb;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardReportDto {

    private String ip;
    private String content;
    private BoardTb boardTb;

}
