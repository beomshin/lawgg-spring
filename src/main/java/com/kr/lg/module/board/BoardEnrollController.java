package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardEnrollController {

    private final BoardService boardService;

    @ApiOperation(value = "포지션 게시판 작성하기", notes = "포지션 게시판 작성합니다.")
    @PostMapping("/position/enroll")
    public ModelAndView positionEnroll(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollPositionRequest request,
            ModelAndView modelAndView,
            HttpServletRequest servletRequest
    ) throws BoardException {
        if (userTb == null) {
            boardService.enrollBoardWithNotLogin(request, ClientUtils.getRemoteIP(servletRequest));
        } else {
            boardService.enrollBoardWithLogin(request, ClientUtils.getRemoteIP(servletRequest), userTb);
        }

        modelAndView.setViewName("redirect:/positions");
        return modelAndView;
    }

}
