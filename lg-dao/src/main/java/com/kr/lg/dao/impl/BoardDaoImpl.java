package com.kr.lg.dao.impl;

import com.kr.lg.dao.BoardDao;
import com.kr.lg.entities.BoardAttachTb;
import com.kr.lg.entities.BoardCommentTb;
import com.kr.lg.entities.BoardTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.DepthEnum;
import com.kr.lg.enums.entity.element.StatusEnum;
import com.kr.lg.model.web.common.listener.BoardCTEvent;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.querydsl.BoardQ;
import com.kr.lg.query.BoardQuery;
import com.kr.lg.repositories.BoardAttachRepository;
import com.kr.lg.repositories.BoardCommentRepository;
import com.kr.lg.repositories.BoardRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardDaoImpl implements BoardDao {

    private final BoardQuery boardQuery;
    private final BoardRepository boardRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardAttachRepository boardAttachRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Page<BoardQ> findAllBoardList(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardQuery.findAllBoardList(requestDto, pageable); // 게시판 content
        JPAQuery<Long> count = boardQuery.findAllBoardListCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<BoardQ> findUserBoardList(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardQuery.findUserBoardList(requestDto, pageable); // 게시판 content
        JPAQuery<Long> count = boardQuery.findUserBoardListCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<BoardQ> findLawFirmBoardList(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardQuery.findLawFirmBoardList(requestDto, pageable); // 게시판 content
        JPAQuery<Long> count = boardQuery.findLawFirmBoardListCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public BoardQ findBoardDetail(BoardLayer requestDto) throws LgException {
        BoardQ board = Optional.ofNullable(boardQuery.findBoardDetail(requestDto).fetchOne()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // board 정보 조회
        board.neUse(); // 정지 || 삭제 게시판 체크
        applicationEventPublisher.publishEvent(new BoardCTEvent(requestDto.getId(), requestDto.getIp())); // 조회수 증가 이벤트
        board.setFiles(boardQuery.findBoardFiles(requestDto).fetch()); // file 세팅
        return board;
    }

    @Override
    public BoardTb saveBoard(BoardLayer requestDto) {
        BoardTb boardTb = boardRepository.save(BoardTb.builder()
                .userTb(requestDto.getUserTb())
                .lawFirmTb(requestDto.getLawFirmTb())
                .postType(requestDto.getPostType())
                .password(requestDto.getPassword())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(requestDto.getWriter())
                .writerType(requestDto.getWriterType())
                .lineType(requestDto.getLineType())
                .ip(requestDto.getIp())
                .build()); // 게시판 저장
        boardCommentRepository.save(BoardCommentTb.builder()
                .boardTb(boardTb)
                .depth(DepthEnum.ROOT_COMMENT)
                .build()); // 루트 댓글 저장
        List<BoardAttachTb> boardAttachTbs = requestDto.getFiles().stream().filter(it -> it != null)
                .map(it -> BoardAttachTb.builder()
                        .boardId(boardTb)
                        .path(it.getPath())
                        .oriName(it.getOriName())
                        .newName(it.getNewName())
                        .size(it.getSize())
                        .status(StatusEnum.NORMAL_STATUS)
                        .build()).collect(Collectors.toList());
        boardAttachRepository.saveAll(boardAttachTbs);
        return boardTb;
    }

    @Override
    public int updateBoard(BoardLayer requestDto) {
        int result = boardRepository.updateBoard(requestDto.getId(), EmojiParser.parseToAliases(requestDto.getTitle()), requestDto.getContent()); // 게시판 업데이트
        List<BoardAttachTb> boardAttachTbs = requestDto.getFiles().stream().filter(it -> it != null)
                .map(it -> BoardAttachTb.builder()
                        .boardId(BoardTb.builder().boardId(requestDto.getId()).build())
                        .path(it.getPath())
                        .oriName(it.getOriName())
                        .newName(it.getNewName())
                        .size(it.getSize())
                        .status(StatusEnum.NORMAL_STATUS)
                        .build()).collect(Collectors.toList());
        boardAttachRepository.saveAll(boardAttachTbs);
        return result;
    }
}
