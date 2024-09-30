package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.model.event.BoardCountEvent;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.model.req.FindPositionRequest;
import com.kr.lg.module.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "BoardFindController", description = "포지션 게시판 조회 컨트롤러")
public class BoardFindController {

    private final BoardService boardService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/positions")
    @Operation(summary = "포지션 게시판 페이지 조회", description = "포지션 게시판 페이지를 조회합니다.")
    public ModelAndView positions(
            @Valid @ModelAttribute FindPositionRequest request, ModelAndView mav
    ) throws BoardException {
        Page<BoardEntry> positions = boardService.findBoards(request);
        positions.getContent().forEach(BoardEntry::additionalContent); // 필요 정보 재세팅

        mav.addObject("positions", positions);
        mav.addObject("query", request);
        mav.addObject("maxPage", 5);

        mav.setViewName("view/position/list");
        return mav;
    }

    @GetMapping("/position/{id}")
    @Operation(summary = "포지션 게시판 상세 페이지 조회", description = "포지션 게시판 상세 페이지를 조회합니다.")
    public ModelAndView position(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Parameter(description = "게시판 아이디", required = true) @PathVariable("id") Long id,
            ModelAndView mav,
            HttpServletRequest request
    ) throws BoardException {
        mav.addObject("position", userTb == null ?
                boardService.findBoardWithNotLogin(id) : boardService.findBoardWithLogin(id, userTb));
        applicationEventPublisher.publishEvent(new BoardCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가 이벤트

        mav.setViewName("view/position/view");
        return mav;
    }

    @GetMapping("/position/write")
    @Operation(summary = "포지션 게시판 작성 페이지 조회", description = "포지션 게시판 작성 페이지를 조회합니다.")
    public ModelAndView writePosition(
            @Parameter(description = "라인 타입") @RequestParam(value = "type", required = false, defaultValue = "0") int type,
            ModelAndView mav
    ) {
        mav.addObject("type", type);
        mav.setViewName("view/position/write");
        return mav;
    }

    @Secured("ROLE_USER")
    @Operation(summary = "포지션 게시판 수정 페이지 호출", description = "포지션 게시판 수정 페이지를 호출합니다.")
    @GetMapping("/position/modify/{id}")
    public ModelAndView modifyPosition(
            ModelAndView mav,
            @Parameter(description = "게시판 아이디") @PathVariable("id") Long boardId,
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws BoardException {
        mav.addObject("position", boardService.findBoard(boardId, userTb));
        mav.setViewName("view/position/update");
        return mav;
    }

}

