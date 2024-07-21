package com.kr.lg.module.board.model.dto;

import com.kr.lg.db.entities.BoardAttachTb;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardAttachEntry {

    private long boardAttachId; // boardAttach 식별자
    private String path; // URL
    private String oriName; // 원본 파일명
    private String newName; // 변경 파일명
    private long size; // 파일 사이즈

    public BoardAttachEntry(BoardAttachTb boardAttachTb) {
        this.boardAttachId = boardAttachTb.getBoardAttachId();
        this.path = boardAttachTb.getPath();
        this.oriName = boardAttachTb.getOriName();
        this.newName = boardAttachTb.getNewName();
        this.size = boardAttachTb.getSize();
    }
}
