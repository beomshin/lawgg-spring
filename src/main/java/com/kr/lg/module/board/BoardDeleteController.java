package com.kr.lg.module.board;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.DeletePositionRequest;
import com.kr.lg.module.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardDeleteController {

    private final BoardService boardService;

    @Secured("ROLE_USER")
    @PostMapping("/position/delete")
    @ApiOperation(value = "포지션 게시판 삭제하기", notes = "포지션 게시판 삭제합니다.")
    public ModelAndView deletePosition(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeletePositionRequest request,
            ModelAndView modelAndView
    ) throws BoardException {
        boardService.deleteBoardWithLogin(request, userTb);
        modelAndView.setViewName("redirect:/positions");
        return modelAndView;
    }

}
