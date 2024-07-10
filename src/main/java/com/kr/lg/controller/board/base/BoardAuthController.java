package com.kr.lg.controller.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.base.LoginABRequest;
import com.kr.lg.model.net.request.board.base.LoginUBRequest;
import com.kr.lg.service.board.base.BoardAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardAuthController {

    private final BoardAuthService boardAuthService;

    @PostMapping("/api/public/board/login/anonymous")
    @ApiOperation(value = "익명 포지션 게시판 로그인", notes = "익명 포지션 게시판 로그인을 합니다.")
    public ResponseEntity<DefaultResponse> loginAnonymousBoard(
            @RequestBody @Validated LoginABRequest request
    ) throws LgException {
        boardAuthService.loginAnonymousBoard(new BoardLayer(request.getPassword(), request.getId()));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/board/login/user")
    @ApiOperation(value = "회원 포지션 게시판 로그인", notes = "회원 포지션 게시판 로그인을 합니다.")
    public ResponseEntity<DefaultResponse> loginUserBoard(
            @RequestBody @Validated LoginUBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boardAuthService.loginUserBoard(new BoardLayer(request, userAdapter.getUserTb()));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
