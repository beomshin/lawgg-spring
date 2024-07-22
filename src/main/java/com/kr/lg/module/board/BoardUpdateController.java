package com.kr.lg.module.board;

import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.module.board.model.req.UpdateBoardWithNotLoginRequest;
import com.kr.lg.module.board.model.req.UpdateBoardWithLoginRequest;
import com.kr.lg.web.dto.root.SuccessResponse;
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
public class BoardUpdateController {

    private final BoardService boardService;

    @PostMapping("/api/public/v1/update/board")
    @ApiOperation(value = "비로그인 포지션 게시판 수정", notes = "비로그인 포지션 게시판 수정을 합니다.")
    public ResponseEntity<?> updateBoardWithNotLogin(
            @RequestBody @Valid UpdateBoardWithNotLoginRequest request
    ) throws BoardException {
        boardService.updateBoardWithNotLogin(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/update/board")
    @ApiOperation(value = "로그인 포지션 게시판 수정", notes = "로그인 포지션 게시판 수정을 합니다.")
    public ResponseEntity<?> updateBoardWithLogin(
            @RequestBody @Valid UpdateBoardWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.updateBoardWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok(new SuccessResponse());
    }

}
