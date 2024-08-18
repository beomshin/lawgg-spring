package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.board.model.event.BoardCountEvent;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.module.board.model.req.FindPositionRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
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
public class BoardFindController {

    private final BoardService boardService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @ApiOperation(value = "포지션 게시판 페이지 호출", notes = "포지션 게시판 페이지를 호출합니다.")
    @GetMapping("/positions")
    public ModelAndView positions(
            @Valid @ModelAttribute FindPositionRequest request, ModelAndView modelAndView
    ) throws BoardException {
        Page<BoardEntry> positions = boardService.findBoards(request);
        positions.getContent().forEach(BoardEntry::additionalContent); // 필요 정보 재세팅

        modelAndView.addObject("positions", positions);
        modelAndView.addObject("query", request);
        modelAndView.addObject("maxPage", 10);

        modelAndView.setViewName("view/position/list");
        return modelAndView;
    }

    @ApiOperation(value = "포지션 게시판 상세 페이지 호출", notes = "포지션 게시판 상세 페이지를 호출합니다.")
    @GetMapping("/position/{id}")
    public ModelAndView position(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long id,
            ModelAndView modelAndView,
            HttpServletRequest request
    ) throws BoardException {
        modelAndView.addObject("position", userTb == null ?
                boardService.findBoardWithNotLogin(id) : boardService.findBoardWithLogin(id, userTb));
        applicationEventPublisher.publishEvent(new BoardCountEvent(id, ClientUtils.getRemoteIP(request))); // 조회수 증가 이벤트

        modelAndView.setViewName("view/position/view");
        return modelAndView;
    }

    @ApiOperation(value = "포지션 게시판 작성 페이지 호출", notes = "포지션 게시판 작성 페이지를 호출합니다.")
    @GetMapping("/position/write")
    public ModelAndView writePosition(
            @RequestParam(value = "type", required = false, defaultValue = "0") int type,
            ModelAndView modelAndView
    ) {
        modelAndView.addObject("type", type);
        modelAndView.setViewName("view/position/write");
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "포지션 게시판 수정 페이지 호출", notes = "포지션 게시판 수정 페이지를 호출합니다.")
    @GetMapping("/position/modify/{id}")
    public ModelAndView modifyPosition(
            ModelAndView modelAndView,
            @ApiParam(value = "게시판 아이디", required = true) @PathVariable("id") Long boardId,
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb
    ) throws BoardException {
        modelAndView.addObject("position", boardService.findBoard(boardId, userTb));
        modelAndView.setViewName("view/position/update");
        return modelAndView;
    }

}

