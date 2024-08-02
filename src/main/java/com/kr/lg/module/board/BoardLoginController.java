package com.kr.lg.module.board;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.board.model.req.LoginBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.LoginBoardWithLoginRequest;
import com.kr.lg.model.global.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardLoginController {

    private final BoardService boardService;

    @PostMapping("/api/public/v1/login/board")
    @ApiOperation(value = "비로그인 포지션 게시판 로그인", notes = "비로그인 포지션 게시판 로그인을 합니다.")
    public ResponseEntity<?> loginBoardWithNotLogin(
            @RequestBody @Valid LoginBoardWithNotLoginRequest request
    ) throws BoardException {
        boardService.loginBoardWithNotLogin(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/board/login/board")
    @ApiOperation(value = "로그인 포지션 게시판 로그인", notes = "로그인 포지션 게시판 로그인을 합니다.")
    public ResponseEntity<?> loginBoardWithLogin(
            @RequestBody @Valid LoginBoardWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.loginBoardWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }
}
