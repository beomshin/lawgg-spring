package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.BoardAttachRepository;
import com.kr.lg.enums.BoardTopicEnum;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.mapper.BoardCommentMapper;
import com.kr.lg.module.board.model.board.FindBoardParamData;
import com.kr.lg.module.board.model.dto.BoardAttachEntry;
import com.kr.lg.module.board.model.dto.BoardCommentEntry;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import com.kr.lg.module.board.service.BoardFindService;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.BoardParam;
import com.kr.lg.module.board.model.board.FindBoardsParamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardFindService boardFindService;
    private final BoardAttachRepository boardAttachRepository;
    private final BoardCommentMapper boardCommentMapper;

    /**
     * 포지션 게시판 조회 리스트
     * @param request
     * @return
     * @throws BoardException
     */
    @Override
    public Page<BoardEntry> findBoards(FindBoardRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] 게시판 리스트 조회");
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindBoardsParamData.builder()
                .type(request.getType())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build(); // mapper param 생성

        return boardFindService.findBoards(new BoardParam<>(param, pageable));
    }

    /**
     * 나의 포지션 게시판 리스트 조회 메소드
     * @param request
     * @param userTb
     * @return
     * @throws BoardException
     */
    @Override
    public Page<BoardEntry> findMyBoards(FindMyBoardRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] 나의 게시판 리스트 조회");
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindBoardsParamData.builder()
                .type(request.getType())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .userId(userTb.getUserId())
                .build(); // mapper param 생성

        return boardFindService.findBoards(new BoardParam<>(param, pageable));
    }

    /**
     * 로펌 포지션 게시판 리스트 조회 메소드
     * @param request
     * @return
     * @throws BoardException
     */
    @Override
    public Page<BoardEntry> findLawFirmBoards(FindLawFirmBoardRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] 로펌 게시판 리스트 조회");
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindBoardsParamData.builder()
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .lawFirmId(request.getId())
                .build(); // mapper param 생성

        return boardFindService.findBoards(new BoardParam<>(param, pageable));
    }

    /**
     * 비로그인 게시판 상세조회
     * @param boardId
     * @return
     * @throws BoardException
     */
    @Override
    public BoardEntry findBoard(long boardId) throws BoardException {
        log.info("▶ [포지션 게시판] 게시판 상세 조회(비로그인)");
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .build();
        BoardEntry board = this.findBoard(param);
        board.setComments(boardCommentMapper.findBoardCommentsWithNotLogin(board.getBoardCommentId()));
        return board;
    }

    /**
     * 로그인 게시판 상세조회
     * @param boardId
     * @param userTb
     * @return
     * @throws BoardException
     */
    @Override
    public BoardEntry findBoard(long boardId, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] 게시판 상세 조회(로그인)");
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .userId(userTb.getUserId())
                .build();
        BoardEntry board = this.findBoard(param);
        board.setComments(boardCommentMapper.findBoardCommentsWithLogin(board.getBoardCommentId()));
        return board;
    }

    /**
     * 정렬 순서 세팅 메소드
     * @param topic
     * @return
     */
    private Sort getSort(int topic) {
        if (BoardTopicEnum.HOT_TOPIC == BoardTopicEnum.of(topic)) {
            return BoardSort.notificationSortWithDesc().and(BoardSort.dateWithDesc()).and(BoardSort.hotDesc());
        } else {
            return BoardSort.notificationSortWithDesc().and(BoardSort.dateTimeWithDesc());
        }
    }

    /**
     * 포지션 게시판 상세 조회 및 상태 검증
     * @param param
     * @return
     * @throws BoardException
     */
    private BoardEntry findBoard(MapperParam param) throws BoardException {
        Optional<BoardEntry> boardEntry = boardFindService.findBoard(param);
        if (boardEntry.isPresent() && boardEntry.get().isNormalStatus()) { // 게시판 존재 & 정상 상태
            List<BoardAttachEntry> files = boardAttachRepository.findByBoardTb_BoardId(boardEntry.get().getBoardId()) // 파일리스트 조회
                    .stream().map(BoardAttachEntry::new).collect(Collectors.toList());
            boardEntry.get().setFiles(files);
            return boardEntry.get();
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재
        }
    }
}
