package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "BoardEnrollController", description = "포지션 게시판 등록 컨트롤러")
public class BoardEnrollController {

    private final BoardService boardService;


    @PostMapping("/position/enroll")
    @Operation(summary = "포지션 게시판 등록하기", description = "포지션 게시판을 등록합니다.")
    public ModelAndView enrollPosition(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollPositionRequest request,
            ModelAndView mav,
            HttpServletRequest servletRequest
    ) throws BoardException {
        if (userTb == null) {
            boardService.enrollBoardWithNotLogin(request, ClientUtils.getRemoteIP(servletRequest));
        } else {
            boardService.enrollBoardWithLogin(request, ClientUtils.getRemoteIP(servletRequest), userTb);
        }

        mav.setViewName("redirect:/positions");
        return mav;
    }

}
