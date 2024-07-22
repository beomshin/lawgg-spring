package com.kr.lg.controller.board.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.base.DeleteABRequest;
import com.kr.lg.model.net.request.board.base.DeleteUBRequest;
import com.kr.lg.service.board.base.BoardDeleteService;
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
public class BoardDeleteController {
    private final BoardDeleteService boardDeleteService;

    @PostMapping("/api/public/board/delete/anonymous")
    @ApiOperation(value = "익명 포지션 게시판 삭제", notes = "익명 포지션 게시판 삭제를 합니다.")
    public ResponseEntity<DefaultResponse> deleteAnonymousBoard(
            @RequestBody @Validated DeleteABRequest request
    ) throws LgException {
        int result = boardDeleteService.deleteAnonymousBoard(new BoardLayer(request));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_BOARD);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/board/delete/user")
    @ApiOperation(value = "회원 포지션 게시판 삭제", notes = "회원 포지션 게시판 삭제를 합니다.")
    public ResponseEntity<DefaultResponse> deleteUserBoard(
            @RequestBody @Validated DeleteUBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = boardDeleteService.deleteUserBoard(new BoardLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_BOARD);
        return ResponseEntity.ok(body);
    }

}
