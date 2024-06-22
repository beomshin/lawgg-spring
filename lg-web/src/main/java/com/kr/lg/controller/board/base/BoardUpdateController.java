package com.kr.lg.controller.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.model.web.net.request.board.base.UpdateABRequest;
import com.kr.lg.model.web.net.request.board.base.UpdateUBRequest;
import com.kr.lg.service.board.base.BoardUpdateService;
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
public class BoardUpdateController {

    private final BoardUpdateService boardUpdateService;


    @PostMapping("/api/board/update/user")
    @ApiOperation(value = "회원 포지션 게시판 수정", notes = "회원 포지션 게시판 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateUserBoard(
            @RequestBody @Validated UpdateUBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = boardUpdateService.updateUserBoard(new BoardLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_UPDATE_BOARD);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/public/board/update/anonymous")
    @ApiOperation(value = "익명 포지션 게시판 수정", notes = "익명 포지션 게시판 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateAnonymousBoard(
            @RequestBody @Validated UpdateABRequest request
    ) throws LgException {
        int result = boardUpdateService.updateAnonymousBoard(new BoardLayer(request));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_UPDATE_BOARD);
        return ResponseEntity.ok(body);
    }

}
