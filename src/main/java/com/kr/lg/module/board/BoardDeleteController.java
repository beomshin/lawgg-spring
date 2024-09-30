package com.kr.lg.module.board;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.DeletePositionRequest;
import com.kr.lg.module.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "BoardDeleteController", description = "포지션 게시판 삭제 컨트롤러")
public class BoardDeleteController {

    private final BoardService boardService;

    @Secured("ROLE_USER")
    @PostMapping("/position/delete")
    @Operation(summary = "포지션 게시판 삭제하기", description = "포지션 게시판 삭제합니다.")
    public ModelAndView deletePosition(
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeletePositionRequest request,
            ModelAndView mav
    ) throws BoardException {
        boardService.deleteBoardWithLogin(request, userTb);
        mav.setViewName("redirect:/positions");
        return mav;
    }

}
