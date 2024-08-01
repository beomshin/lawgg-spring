package com.kr.lg.module.board.model.dto;


import com.kr.lg.web.dto.global.FileDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardUpdateDto {

    private long boardId;
    private String title;
    private String content;
    private List<FileDto> files;

}
