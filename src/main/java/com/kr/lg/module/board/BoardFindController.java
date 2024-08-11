package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.board.model.event.BoardCountEvent;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import com.kr.lg.module.board.model.res.FindBoardResponse;
import com.kr.lg.module.board.model.res.FindBoardsResponse;
import com.kr.lg.module.board.model.res.FindLawFirmBoardsResponse;
import com.kr.lg.module.board.model.res.FindMyBoardsResponse;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.model.common.AbstractSpec;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardFindController {

    private final BoardService boardService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ApiOperation(value = "포지션 게시판 페이지 호출", notes = "포지션 게시판 페이지를 호출합니다.")

    @GetMapping("/positions")
    public ModelAndView positions(
            @Valid @ModelAttribute FindBoardRequest request, ModelAndView modelAndView
    ) throws BoardException {
        Page<BoardEntry> positions = boardService.findBoards(request);
        positions.getContent().forEach(BoardEntry::additionalContent); // 필요 정보 재세팅

        modelAndView.addObject("positions", positions);
        modelAndView.addObject("query", request);
        modelAndView.addObject("maxPage", 10);

        modelAndView.setViewName("view/position/list");
        return modelAndView;
    }


    @ApiOperation(value = "나의 포지션 게시판 조회", notes = "나의 포지션 게시판을 조회합니다.")
    @GetMapping("/api/v1/find/my/boards")
    public ResponseEntity<?> findMyBoards(
            @Valid FindMyBoardRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException { // 미사용 기능
        Page<BoardEntry> boards = boardService.findMyBoards(request, userAdapter.getUserTb());

        AbstractSpec spec = FindMyBoardsResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }

    @ApiOperation(value = "로펌 포지션 게시판 조회", notes = "로펌 포지션 게시판을 조회합니다.")
    @GetMapping("/api/public/v1/find/law-firm/boards")
    public ResponseEntity<?> findLawFirmBoards(
            @Valid FindLawFirmBoardRequest request
    ) throws BoardException {
        Page<BoardEntry> boards = boardService.findLawFirmBoards(request);

        AbstractSpec spec = FindLawFirmBoardsResponse.builder()
                .list(boards.getContent())
                .totalElements(boards.getTotalElements())
                .totalPage(boards.getTotalPages())
                .curPage(boards.getNumber())
                .build();

        return ResponseEntity.ok().body(spec);
    }

    @GetMapping("/api/public/v1/find/board/{id}")
    @ApiOperation(value = "비로그인 포지션 게시판 상세 조회", notes = "포지션 게시판 상세 조회 조회합니다.")
    public ResponseEntity<?> findBoardWithNotLogin(
            HttpServletRequest request,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id
    ) throws BoardException {
        BoardEntry boardEntry = boardService.findBoardWithNotLogin(id);
        applicationEventPublisher.publishEvent(new BoardCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가 이벤트
        AbstractSpec spec = FindBoardResponse.builder()
                .board(boardEntry)
                .build();
        return ResponseEntity.ok().body(spec);
    }


    @GetMapping("/api/v1/find/board/{id}")
    @ApiOperation(value = "로그인 포지션 게시판 상세 조회", notes = "포지션 게시판 상세 조회 조회합니다.")
    public ResponseEntity<?> findBoardWithLogin(
            HttpServletRequest request,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        BoardEntry boardEntry = boardService.findBoardWithLogin(id, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new BoardCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가 이벤트
        AbstractSpec spec = FindBoardResponse.builder()
                .board(boardEntry)
                .build();
        return ResponseEntity.ok().body(spec);
    }

}

