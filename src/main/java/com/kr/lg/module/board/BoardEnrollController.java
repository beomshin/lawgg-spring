package com.kr.lg.module.board;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.board.model.req.EnrollBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.EnrollBoardWithLawFirmLoginRequest;
import com.kr.lg.module.board.model.req.EnrollBoardWithLoginRequest;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardEnrollController {

    private final BoardService boardService;

    @PostMapping("/api/public/v1/enroll/board")
    @ApiOperation(value = "비로그인 포지션 게시판 등록", notes = "비로그인 포지션 게시판 등록합니다.")
    public ResponseEntity<?> enrollBoardWithNotLogin(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid EnrollBoardWithNotLoginRequest request
    ) throws BoardException {
        boardService.enrollBoardWithNotLogin(request, ClientUtils.getRemoteIP(httpServletRequest));
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/enroll/board")
    @ApiOperation(value = "로그인 포지션 게시판 등록", notes = "로그인 포지션 게시판 등록합니다.")
    public ResponseEntity<?> enrollBoardWithLogin(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid EnrollBoardWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.enrollBoardWithLogin(request, ClientUtils.getRemoteIP(httpServletRequest), userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/enroll/law-firm/board")
    @ApiOperation(value = "로펌 포지션 게시판 등록", notes = "로펌 포지션 게시판 등록합니다.")
    public ResponseEntity<?> enrollBoardWithLawFirmLogin(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid EnrollBoardWithLawFirmLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.enrollBoardWithLawFirmLogin(request, ClientUtils.getRemoteIP(httpServletRequest), userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
