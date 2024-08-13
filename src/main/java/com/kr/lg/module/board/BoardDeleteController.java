package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.DeleteBoardWithLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.EnrollPositionRequest;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

//    @PostMapping("/api/public/v1/delete/board")
//    @ApiOperation(value = "비로그인 포지션 게시판 삭제", notes = "비로그인 포지션 게시판 삭제를 합니다.")
//    public ResponseEntity<?> deleteBoardWithNotLogin(
//            @RequestBody @Valid DeleteBoardWithNotLoginRequest request
//    ) throws BoardException {
//        boardService.deleteBoardWithNotLogin(request);
//        return ResponseEntity.ok(new SuccessResponse());
//    }
//
//    @PostMapping("/api/v1/delete/board")
//    @ApiOperation(value = "로그인 포지션 게시판 삭제", notes = "로그인 포지션 게시판 삭제를 합니다.")
//    public ResponseEntity<?> deleteBoardWithLogin(
//            @RequestBody @Valid DeleteBoardWithLoginRequest request,
//            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
//    ) throws BoardException {
//        boardService.deleteBoardWithLogin(request, userAdapter.getUserTb());
//        return ResponseEntity.ok(new SuccessResponse());
//    }


}
