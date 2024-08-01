package com.kr.lg.module.board.model.dto;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.LineEnum;
import com.kr.lg.enums.PostEnum;
import com.kr.lg.enums.WriterEnum;
import com.kr.lg.web.dto.global.FileDto;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardEnrollDto {

    private UserTb userTb; // 유저 엔티티
    private LawFirmTb lawFirmTb; // 로펌 엔티티
    private PostEnum postType; // 게시글 글 타입
    private String password; // 패스워드
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private WriterEnum writerType; // 작성자 타입
    private LineEnum lineType; // 라인 타입
    private String ip; // ip
    private List<FileDto> files;
    private BoardTb boardTb;

    public boolean hasPassword() {
        return StringUtils.isNotBlank(password);
    }
}
