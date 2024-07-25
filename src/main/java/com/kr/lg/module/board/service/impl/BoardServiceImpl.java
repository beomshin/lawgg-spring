package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.BoardRecommendTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.BoardAttachRepository;
import com.kr.lg.db.repositories.BoardRecommendRepository;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.enums.*;
import com.kr.lg.module.board.model.req.*;
import com.kr.lg.module.board.model.dto.BoardReportDto;
import com.kr.lg.module.board.model.dto.BoardEnrollDto;
import com.kr.lg.module.board.model.dto.BoardUpdateDto;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.mapper.BoardCommentMapper;
import com.kr.lg.module.board.model.mapper.FindBoardParamData;
import com.kr.lg.module.board.model.entry.BoardAttachEntry;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.service.*;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.BoardParam;
import com.kr.lg.module.board.model.mapper.FindBoardsParamData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final BoardRepository boardRepository;
    private final BoardCommentMapper boardCommentMapper;

    private final BCryptPasswordEncoder encoder;

    /**
     * 포지션 게시판 조회 리스트
     * @param request
     * @return
     * @throws BoardException
     */
    @Override
    public Page<BoardEntry> findBoards(FindBoardRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] findBoards 메소드 실행");
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
        log.info("▶ [포지션 게시판] findMyBoards 메소드 실행");
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
        log.info("▶ [포지션 게시판] findLawFirmBoards 메소드 실행");
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
    public BoardEntry findBoardIdWithNotLogin(long boardId) throws BoardException {
        log.info("▶ [포지션 게시판] findBoardIdWithNotLogin 메소드 실행");
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
    public BoardEntry findBoardIdWithLogin(long boardId, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] findBoardIdWithLogin 메소드 실행");
        MapperParam param = FindBoardParamData.builder()
                .boardId(boardId)
                .userId(userTb.getUserId())
                .build();
        BoardEntry board = this.findBoard(param);
        board.setComments(boardCommentMapper.findBoardCommentsWithLogin(board.getBoardCommentId(), userTb.getUserId()));
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
    public void enrollBoardWithNotLogin(EnrollBoardWithNotLoginRequest request, String ip) throws BoardException {
        log.info("▶ [포지션 게시판] enrollBoardWithNotLogin 메소드 실행");
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        BoardEnrollDto board = BoardEnrollDto.builder()
                .password(request.getPassword())
                .postType(isEnrollFile ? PostEnum.IMAGE_TYPE : PostEnum.NORMAL_TYPE)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getId())
                .lineType(LineEnum.of(request.getLineType()))
                .writerType(WriterEnum.ANONYMOUS_TYPE)
                .ip(ip)
                .build();

        log.info("▶ [포지션 게시판] 포지션 게시판 등록(비로그인)");
        BoardTb boardTb = boardEnrollService.enrollBoard(board);
        if (isEnrollFile) {
            boardEnrollService.enrollBoardFiles(boardTb, request.getFiles());
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
    public void enrollBoardWithLogin(EnrollBoardWithLoginRequest request, String ip, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] enrollBoardWithLogin 메소드 실행");

        if (userTb == null) throw new BoardException(BoardResultCode.NOT_EXIST_USER); // 로그인 필수
        boolean isEnrollLawFirm = request.getIsLawFirm() != null && request.getIsLawFirm() == 1;
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        BoardEnrollDto board = BoardEnrollDto.builder()
                .userTb(userTb)
                .lawFirmTb(isEnrollLawFirm ? userTb.getLawFirmId() : null)
                .postType(isEnrollFile ? PostEnum.IMAGE_TYPE : PostEnum.NORMAL_TYPE)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(userTb.getNickName())
                .lineType(LineEnum.of(request.getLineType()))
                .writerType(WriterEnum.MEMBER_TYPE)
                .ip(ip)
                .build();

        BoardTb boardTb = boardEnrollService.enrollBoard(board);
        if (isEnrollFile) {
            boardEnrollService.enrollBoardFiles(boardTb, request.getFiles());
        }
    }

    /**
     * 로펌 포지션 게시판 등록
     * @param request
     * @param ip
     * @param userTb
     * @throws BoardException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void enrollBoardWithLawFirmLogin(EnrollBoardWithLawFirmLoginRequest request, String ip, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] enrollBoardWithLawFirmLogin 메소드 실행");

        if (userTb == null) throw new BoardException(BoardResultCode.NOT_EXIST_USER); // 로그인 필수
        else if (userTb.getLawFirmId() == null) throw new BoardException(BoardResultCode.NOT_EXIST_LAW_FIRM); // 로펌 필수
        else if (!Objects.equals(userTb.getLawFirmId().getLawFirmId(), request.getId())) throw new BoardException(BoardResultCode.UN_MATCHED_LAW_FIRM_USER);
        boolean isEnrollFile = request.getFiles() != null && !request.getFiles().isEmpty();

        BoardEnrollDto board = BoardEnrollDto.builder()
                .userTb(userTb)
                .lawFirmTb(userTb.getLawFirmId())
                .postType(isEnrollFile ? PostEnum.IMAGE_TYPE : PostEnum.NORMAL_TYPE)
                .title(request.getTitle())
                .content(request.getContent())
                .writer(userTb.getNickName())
                .lineType(LineEnum.of(request.getLineType()))
                .writerType(WriterEnum.LAW_FIRM_TYPE)
                .ip(ip)
                .build();

        BoardTb boardTb = boardEnrollService.enrollBoard(board);
        if (isEnrollFile) {
            boardEnrollService.enrollBoardFiles(boardTb, request.getFiles());
        }

    }

    /**
     * 비로그인 포지션 게시판 수정
     * @param request
     * @throws BoardException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateBoardWithNotLogin(UpdateBoardWithNotLoginRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] updateBoardWithNotLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterType(request.getId(), WriterEnum.ANONYMOUS_TYPE);
        if (boardTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), boardTb.get().getPassword())) throw new BoardException(BoardResultCode.UN_MATCH_PASSWORD);
            boolean isEnrollFile = request.getAddFiles() != null && !request.getAddFiles().isEmpty();

            boardUpdateService.updateBoard(BoardUpdateDto.builder()
                            .boardId(boardTb.get().getBoardId())
                            .title(request.getTitle())
                            .content(request.getContent())
                    .build());
            if (isEnrollFile) {
                boardEnrollService.enrollBoardFiles(boardTb.get(), request.getAddFiles());
            }
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재

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
    public void updateBoardWithLogin(UpdateBoardWithLoginRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] updateBoardWithLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterType(request.getId(), WriterEnum.MEMBER_TYPE);
        if (boardTb.isPresent()) {
            if (!userTb.getUserId().equals(boardTb.get().getUserTb().getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER);
            boolean isEnrollFile = request.getAddFiles() != null && !request.getAddFiles().isEmpty();

            boardUpdateService.updateBoard(BoardUpdateDto.builder()
                    .boardId(boardTb.get().getBoardId())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .build());
            if (isEnrollFile) {
                boardEnrollService.enrollBoardFiles(boardTb.get(), request.getAddFiles());
            }
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재

        }
    }

    /**
     * 비로그인 포지션 게시판 삭제
     * @param request
     * @throws BoardException
     */
    @Override
    public void deleteBoardWithNotLogin(DeleteBoardWithNotLoginRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] deleteBoardWithNotLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterTypeAndStatus(request.getId(), WriterEnum.ANONYMOUS_TYPE, StatusEnum.NORMAL_STATUS);
        if (boardTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), boardTb.get().getPassword())) throw new BoardException(BoardResultCode.UN_MATCH_PASSWORD);
            boardDeleteService.deleteBoard(boardTb.get().getBoardId());
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
    public void deleteBoardWithLogin(DeleteBoardWithLoginRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] deleteBoardWithLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterTypeAndStatus(request.getId(), WriterEnum.MEMBER_TYPE, StatusEnum.NORMAL_STATUS);
        if (boardTb.isPresent()) {
            boolean isBestOrRecommendBoard = boardTb.get().getPostType().equals(PostEnum.BEST_TYPE) || boardTb.get().getPostType().equals(PostEnum.RECOMMEND); // 베스트 or 추천 게시판 플래그
            if (!userTb.getUserId().equals(boardTb.get().getUserTb().getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER); // 작성자 체크
            else if (isBestOrRecommendBoard && !encoder.matches(request.getPassword(), boardTb.get().getPassword())) throw new BoardException(BoardResultCode.UN_MATCH_PASSWORD); // 베스트 or 추천 게시판은 패스워드 검증
            boardDeleteService.deleteBoard(boardTb.get().getBoardId());
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
    public void reportBoard(ReportBoardRequest request, String ip) throws BoardException {
        log.info("▶ [포지션 게시판] reportBoard 메소드 실행");
        BoardReportDto reportDto = BoardReportDto.builder()
                .ip(ip)
                .content(request.getContent())
                .boardTb(BoardTb.builder().boardId(request.getId()).build())
                .build();
        boardReportService.reportBoard(reportDto);
    }

    /**
     * 포지션 게시판 추천
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    public void recommendBoard(RecommendBoardRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] recommendBoard 메소드 실행");

        Optional<BoardRecommendTb> boardRecommendTb = boardRecommendRepository.findByBoardTb_BoardIdAndUserTb_UserId(request.getId(), userTb.getUserId()); // 나의 추천 내역 조회
        if (boardRecommendTb.isPresent()) throw new BoardException(BoardResultCode.ALREADY_RECOMMEND_BOARD); // 중복 추천 방어코드
        boardRecommendService.recommendBoard(BoardTb.builder().boardId(request.getId()).build(), userTb); // 게시판 추천
    }

    /**
     * 포지션 게시판 추천 삭제
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    public void deleteRecommendBoard(DeleteRecommendBoardRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] deleteRecommendBoard 메소드 실행");
        boardRecommendService.deleteRecommendBoard(request.getId(), userTb.getUserId()); // 게시판 추천 취소
    }

    /**
     * 비로그인 게시판 로그인
     * @param request
     * @throws BoardException
     */
    @Override
    public void loginBoardWithNotLogin(LoginBoardWithNotLoginRequest request) throws BoardException {
        log.info("▶ [포지션 게시판] loginBoardWithNotLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterType(request.getId(), WriterEnum.ANONYMOUS_TYPE);
        if (boardTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), boardTb.get().getPassword())) throw new BoardException(BoardResultCode.UN_MATCH_PASSWORD);
            log.info("▶ [포지션 게시판] 비로그인 포지션 게시판 로그인");
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재
        }

    }

    /**
     * 로그인 게시판 로그인
     * @param request
     * @param userTb
     * @throws BoardException
     */
    @Override
    public void loginBoardWithLogin(LoginBoardWithLoginRequest request, UserTb userTb) throws BoardException {
        log.info("▶ [포지션 게시판] loginBoardWithLogin 메소드 실행");

        Optional<BoardTb> boardTb = boardRepository.findByBoardIdAndWriterType(request.getId(), WriterEnum.MEMBER_TYPE);
        if (boardTb.isPresent()) {
            if (!userTb.getUserId().equals(boardTb.get().getUserTb().getUserId())) throw new BoardException(BoardResultCode.UN_MATCHED_USER);
            log.info("▶ [포지션 게시판] 로그인 포지션 게시판 로그인");
        } else {
            throw new BoardException(BoardResultCode.NOT_EXIST_BOARD); // 게시판 미존재
        }

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
