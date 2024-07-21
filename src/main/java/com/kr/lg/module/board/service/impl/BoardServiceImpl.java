package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.enums.BoardTopicEnum;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import com.kr.lg.module.board.service.BoardFindService;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.module.board.mapper.board.BoardParam;
import com.kr.lg.module.board.mapper.board.FindBoardMapperParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardFindService boardFindService;

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
        MapperParam param = FindBoardMapperParam.builder()
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
    public Page<BoardEntry> findUserBoards(FindMyBoardRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] 나의 게시판 리스트 조회");
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindBoardMapperParam.builder()
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
        MapperParam param = FindBoardMapperParam.builder()
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .lawFirmId(request.getId())
                .build(); // mapper param 생성

        return boardFindService.findBoards(new BoardParam<>(param, pageable));
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
}
