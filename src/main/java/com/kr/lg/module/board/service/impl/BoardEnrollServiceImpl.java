package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.BoardAttachTb;
import com.kr.lg.db.entities.BoardCommentTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.repositories.BoardAttachRepository;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.enums.*;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.model.dto.BoardEnrollDto;
import com.kr.lg.module.board.service.BoardEnrollService;
import com.kr.lg.web.dto.global.GlobalFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardEnrollServiceImpl implements BoardEnrollService {

    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BCryptPasswordEncoder encoder;
    private final BoardAttachRepository boardAttachRepository;


    @Override
    @Transactional
    public BoardTb enrollBoard(BoardEnrollDto enrollDto) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 등록");
            BoardTb boardTb = BoardTb.builder() // 게시글 엔티티 생성
                    .userTb(enrollDto.getUserTb())
                    .lawFirmTb(enrollDto.getLawFirmTb())
                    .postType(enrollDto.getPostType())
                    .password(enrollDto.hasPassword() ? encoder.encode(enrollDto.getPassword()) : null)
                    .title(enrollDto.getTitle())
                    .content(enrollDto.getContent())
                    .writer(enrollDto.getWriter())
                    .writerType(enrollDto.getWriterType())
                    .lineType(enrollDto.getLineType())
                    .ip(enrollDto.getIp())
                    .build();
            BoardCommentTb commentTb = BoardCommentTb.builder() // 게실글 루트 댓글 엔티티 생성
                    .boardTb(boardTb)
                    .depth(DepthEnum.ROOT_COMMENT)
                    .build();
            boardRepository.save(boardTb); // 게시글 save
            boardCommentRepository.save(commentTb); // 게시글 루트 댓글 save
            return boardTb;
        } catch (Exception e) {
            log.error("", e);
            throw new BoardException(BoardResultCode.FAIL_ENROLL_BOARD);
        }
    }

    @Override
    public <T> void enrollBoardFiles(BoardTb boardTb, List<T> files) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 파일 등록");
            List<BoardAttachTb> boardAttach = files.stream()
                    .filter(Objects::nonNull)
                    .filter(it -> it instanceof GlobalFile)
                    .map(it -> BoardAttachTb.builder()
                            .boardTb(boardTb)
                            .path(((GlobalFile)it).getPath())
                            .oriName(((GlobalFile)it).getOriName())
                            .newName(((GlobalFile)it).getNewName())
                            .size(((GlobalFile)it).getSize())
                            .status(StatusEnum.NORMAL_STATUS)
                            .build()).collect(Collectors.toList());
            boardAttachRepository.saveAll(boardAttach);
        } catch (Exception e) {
            throw new BoardException(BoardResultCode.FAIL_ENROLL_BOARD_IMAGE);
        }
    }

}
