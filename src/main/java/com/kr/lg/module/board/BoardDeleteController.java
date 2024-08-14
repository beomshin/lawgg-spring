package com.kr.lg.module.board;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.DeleteBoardWithNotLoginRequest;
import com.kr.lg.module.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardDeleteController {

    private final BoardService boardService;

    @ApiOperation(value = "포지션 게시판 삭제하기", notes = "포지션 게시판 삭제합니다.")
    @PostMapping("/position/delete")
    public ModelAndView positionEnroll(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeleteBoardWithNotLoginRequest request,
            ModelAndView modelAndView
    ) throws BoardException {
        if (userTb == null) {
            boardService.deleteBoardWithNotLogin(request);
        } else {
            boardService.deleteBoardWithLogin(request, userTb);
        }

        modelAndView.setViewName("redirect:/positions");
        return modelAndView;
    }

}
