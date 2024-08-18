package com.kr.lg.module.board.service.impl;

import com.kr.lg.common.enums.entity.status.BoardStatus;
import com.kr.lg.common.enums.entity.type.LineType;
import com.kr.lg.common.enums.entity.type.PostType;
import com.kr.lg.common.enums.entity.type.WriterType;
import com.kr.lg.common.enums.logic.BoardTopic;
import com.kr.lg.db.entities.BoardRecommendTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.ReportTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.BoardAttachRepository;
import com.kr.lg.db.repositories.BoardRecommendRepository;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.db.repositories.ReportRepository;
import com.kr.lg.model.dto.FileDto;
import com.kr.lg.module.board.model.event.BoardRecommendEvent;
import com.kr.lg.module.board.model.event.UserBoardCreateCountEvent;
import com.kr.lg.module.board.model.req.*;
import com.kr.lg.module.board.model.dto.BoardReportDto;
import com.kr.lg.module.board.model.dto.BoardEnrollDto;
import com.kr.lg.module.board.model.dto.BoardUpdateDto;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.comment.mapper.CommentMapper;
import com.kr.lg.module.board.model.mapper.FindBoardParamData;
import com.kr.lg.module.board.model.entry.BoardAttachEntry;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.service.*;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.model.mapper.BoardParam;
import com.kr.lg.module.board.model.mapper.FindBoardsParamData;
import com.kr.lg.module.thirdparty.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardFindService boardFindService;
    private final BoardEnrollService boardEnrollService;
    private final BoardUpdateService boardUpdateService;
    private final BoardDeleteService boardDeleteService;
    private final BoardReportService boardReportService;
    private final BoardRecommendService boardRecommendService;

    private final BoardAttachRepository boardAttachRepository;
    private final BoardRecommendRepository boardRecommendRepository;
    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;
    private final CommentMapper commentMapper;

    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final FileService<FileDto> fileService;

    /**
     * 포지션 게시판 조회 리스트
     * @param request
     * @return
     * @throws BoardException
     */
    @Override
    public Page<BoardEntry> findBoards(FindPositionRequest request) throws BoardException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindBoardsParamData.builder()
                .type(request.getType())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build(); // mapper param 생성

        return boardFindService.findBoards(new BoardParam<>(param, pageable));
    }

    @Override
    public BoardEntry findBoard(long boardId, UserTb userTb) throws BoardException {
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .userId(userTb.getUserId())
                .build();
        BoardEntry board = this.findBoard(param);
        if (!Objects.equals(board.getUserId(), userTb.getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER);
        return board;
    }

    /**
     * 비로그인 게시판 상세조회
     * @param boardId
     * @return
     * @throws BoardException
     */
    @Override
    public BoardEntry findBoardWithNotLogin(long boardId) throws BoardException {
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .build();
        BoardEntry board = this.findBoard(param);
        board.setComments(commentMapper.findBoardCommentsWithNotLogin(board.getBoardCommentId()));
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
    public BoardEntry findBoardWithLogin(long boardId, UserTb userTb) throws BoardException {
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .userId(userTb.getUserId())
                .build();
        BoardEntry board = this.findBoard(param);
        board.setComments(commentMapper.findBoardCommentsWithLogin(board.getBoardCommentId(), userTb.getUserId()));
        return board;
    }

    /**
     * 비로그인 유저 포지션 게시판 등록
     * @param request
     * @param ip
     * @throws BoardException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void enrollBoardWithNotLogin(EnrollPositionRequest request, String ip) throws BoardException {
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        BoardEnrollDto board = BoardEnrollDto.builder()
                .password(request.getPassword())
                .postType(isEnrollFile ? PostType.IMAGE_TYPE : PostType.NORMAL_TYPE)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getId())
                .lineType(LineType.of(request.getLineType()))
                .writerType(WriterType.ANONYMOUS_TYPE)
                .ip(ip)
                .build();

        log.info("▶ [포지션 게시판] 포지션 게시판 등록(비로그인)");
        BoardTb boardTb = boardEnrollService.enrollBoard(board);
        if (isEnrollFile) {
            boardEnrollService.enrollBoardFiles(boardTb, fileService.uploadMultiple(request.getFiles()));
        }
    }

    /**
     * 로그인 유저 포지션 게시판 등록
     * @param request
     * @param ip
     * @param userTb
     * @throws BoardException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void enrollBoardWithLogin(EnrollPositionRequest request, String ip, UserTb userTb) throws BoardException {
        if (userTb == null) throw new BoardException(BoardResultCode.NOT_EXIST_USER); // 로그인 필수
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        BoardEnrollDto board = BoardEnrollDto.builder()
                .userTb(userTb)
                .lawFirmTb(userTb.getLawFirmTb())
                .postType(isEnrollFile ? PostType.IMAGE_TYPE : PostType.NORMAL_TYPE)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(userTb.getNickName())
                .lineType(LineType.of(request.getLineType()))
                .writerType(WriterType.MEMBER_TYPE)
                .ip(ip)
                .build();

        BoardTb boardTb = boardEnrollService.enrollBoard(board);
        applicationEventPublisher.publishEvent(new UserBoardCreateCountEvent(userTb, 1));
        if (isEnrollFile) {
            boardEnrollService.enrollBoardFiles(boardTb, fileService.uploadMultiple(request.getFiles()));
        }
    }


    /**
     * 로그인 포지션 게시판 수정
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateBoardWithLogin(UpdatePositionRequest request, UserTb userTb) throws BoardException {
        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterType(request.getBoardId(), WriterType.MEMBER_TYPE);
        if (boardTb.isPresent()) {
            if (!userTb.getUserId().equals(boardTb.get().getUserTb().getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER);
            boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

            boardUpdateService.updateBoard(BoardUpdateDto.builder()
                    .boardId(boardTb.get().getBoardId())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .build());
            if (isEnrollFile) {
                boardEnrollService.enrollBoardFiles(boardTb.get(), fileService.uploadMultiple(request.getFiles()));
            }
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재

        }
    }

    /**
     * 로그인 포지션 게시판 삭제
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    @Transactional
    public void deleteBoardWithLogin(DeletePositionRequest request, UserTb userTb) throws BoardException {
        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterTypeAndStatus(request.getBoardId(), WriterType.MEMBER_TYPE, BoardStatus.NORMAL_STATUS);
        if (boardTb.isPresent() && boardTb.get().getWriterType() == WriterType.MEMBER_TYPE) {
            boolean isBestOrRecommendBoard = boardTb.get().getPostType().equals(PostType.BEST_TYPE) || boardTb.get().getPostType().equals(PostType.RECOMMEND); // 베스트 or 추천 게시판 플래그
            if (!userTb.getUserId().equals(boardTb.get().getUserTb().getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER); // 작성자 체크
            else if (isBestOrRecommendBoard && !encoder.matches(request.getPassword(), boardTb.get().getPassword())) throw new BoardException(BoardResultCode.UN_MATCH_PASSWORD); // 베스트 or 추천 게시판은 패스워드 검증
            boardDeleteService.deleteBoard(boardTb.get().getBoardId());
            applicationEventPublisher.publishEvent(new UserBoardCreateCountEvent(userTb, -1));
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재

        }
    }

    /**
     * 포지션 게시판 신고
     * @param request
     * @param ip
     * @throws BoardException
     */
    @Override
    @Transactional
    public void reportBoard(ReportPositionRequest request, String ip) throws BoardException {
        Optional<BoardTb> boardTb = boardRepository.findById(request.getBoardId());
        if (boardTb.isPresent()) {
            Optional<ReportTb> reportTb = reportRepository.findByBoardTbAndIp(boardTb.get(), ip);
            if (reportTb.isPresent()) throw new BoardException(BoardResultCode.ALREADY_REPORT_BOARD); // 신고 완료 상태
            BoardReportDto reportDto = BoardReportDto.builder().ip(ip).boardTb(boardTb.get()).build();
            boardReportService.reportBoard(reportDto);
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재
        }
    }

    /**
     * 포지션 게시판 추천
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    @Transactional
    public void recommendBoard(RecommendPositionRequest request, UserTb userTb) throws BoardException {
        Optional<BoardRecommendTb> boardRecommendTb = boardRecommendRepository.findByBoardTb_BoardIdAndUserTb_UserId(request.getBoardId(), userTb.getUserId()); // 나의 추천 내역 조회
        if (boardRecommendTb.isPresent()) throw new BoardException(BoardResultCode.ALREADY_RECOMMEND_BOARD); // 중복 추천 방어코드
        boardRecommendService.recommendBoard(BoardTb.builder().boardId(request.getBoardId()).build(), userTb); // 게시판 추천
        applicationEventPublisher.publishEvent(new BoardRecommendEvent(request.getBoardId(), 1)); // 추천 수 증가
    }

    /**
     * 정렬 순서 세팅 메소드
     * @param topic
     * @return
     */
    private Sort getSort(int topic) {
        if (BoardTopic.HOT_TOPIC == BoardTopic.of(topic)) {
//            return BoardSort.notificationSortWithDesc().and(BoardSort.dateWithDesc()).and(BoardSort.hotDesc()); // 현재는 게시글이 없기에 날짜 기준 제거
            return BoardSort.notificationSortWithDesc().and(BoardSort.hotDesc()).and(BoardSort.dateTimeWithDesc());
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
            List<BoardAttachEntry> files = boardAttachRepository.findByBoardTb_BoardId(boardEntry.get().getBoardId())
                    .stream().map(BoardAttachEntry::new).collect(Collectors.toList()); // 파일리스트 조회
            boardEntry.get().setFiles(files);
            return boardEntry.get();
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재
        }
    }

}
