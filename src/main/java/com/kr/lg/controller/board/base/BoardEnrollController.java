package com.kr.lg.controller.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.base.EnrollABRequest;
import com.kr.lg.model.net.request.board.base.EnrollLFBRequest;
import com.kr.lg.model.net.request.board.base.EnrollUBRequest;
import com.kr.lg.service.board.base.BoardEnrollService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardEnrollController {

    private final BoardEnrollService boardEnrollService;


    @PostMapping("/api/board/enroll/user")
    @ApiOperation(value = "회원 포지션 게시판 등록", notes = "회원 포지션 게시판을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollUserBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Validated EnrollUBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boolean result = boardEnrollService.enrollUserBoard(new BoardLayer(request, userAdapter.getUserTb(), ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_ENROLL_BOARD);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/public/board/enroll/anonymous")
    @ApiOperation(value = "익명 포지션 게시판 등록", notes = "익명 포지션 게시판을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollAnonymousBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Validated EnrollABRequest request
    ) throws LgException {
        boolean result = boardEnrollService.enrollAnonymousBoard(new BoardLayer(request, ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_ENROLL_BOARD);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/board/enroll/law-firm")
    @ApiOperation(value = "로펌 포지션 게시판 등록", notes = "로펌으로 포지션 게시판을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollLawFirmBoard(
            HttpServletRequest httpServletRequest,
            @RequestBody @Validated EnrollLFBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boolean result = boardEnrollService.enrollLawFirmBoard(new BoardLayer(request, userAdapter.getUserTb(), ClientUtils.getRemoteIP(httpServletRequest)));
        DefaultResponse body = result ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_ENROLL_BOARD);
        return ResponseEntity.ok(body);
    }

}
