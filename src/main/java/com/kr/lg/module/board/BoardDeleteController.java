package com.kr.lg.module.board;

import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.module.board.model.dto.BoardCreateCountEvent;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.req.DeleteBoardWithLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardWithNotLoginRequest;
import com.kr.lg.module.board.service.BoardService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.web.dto.root.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardDeleteController {

    private final BoardService boardService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/api/public/v1/delete/board")
    @ApiOperation(value = "비로그인 포지션 게시판 삭제", notes = "비로그인 포지션 게시판 삭제를 합니다.")
    public ResponseEntity<?> deleteBoardWithNotLogin(
            @RequestBody @Valid DeleteBoardWithNotLoginRequest request
    ) throws BoardException {
        boardService.deleteBoardWithNotLogin(request);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/delete/board")
    @ApiOperation(value = "로그인 포지션 게시판 삭제", notes = "로그인 포지션 게시판 삭제를 합니다.")
    public ResponseEntity<?> deleteBoardWithLogin(
            @RequestBody @Valid DeleteBoardWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws BoardException {
        boardService.deleteBoardWithLogin(request, userAdapter.getUserTb());
        applicationEventPublisher.publishEvent(new BoardCreateCountEvent(userAdapter.getUserTb(), -1));
        return ResponseEntity.ok(new SuccessResponse());
    }


}
