package com.kr.lg.controller.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.comment.UpdateACBRequest;
import com.kr.lg.model.net.request.board.comment.UpdateUCBRequest;
import com.kr.lg.service.board.comment.BoardCommentUpdateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardCommentUpdateController {

    private final BoardCommentUpdateService boardCommentUpdateService;

    @PostMapping("/api/public/board/update/anonymous/comment")
    @ApiOperation(value = "익명 포지션 게시판 댓글 수정", notes = "익명 포지션 게시판 댓글 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateAnonymousCommentBoard(
            @RequestBody UpdateACBRequest request
    ) throws LgException {
        int result = boardCommentUpdateService.updateAnonymousCommentBoard(new BoardLayer(request));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_UPDATE_BOARD_COMMENT);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/board/update/user/comment")
    @ApiOperation(value = "회원 포지션 게시판 댓글 수정", notes = "회원 포지션 게시판 댓글 수정을 합니다.")
    public ResponseEntity<DefaultResponse> updateUserCommentBoard(
            @RequestBody UpdateUCBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = boardCommentUpdateService.updateUserCommentBoard(new BoardLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_UPDATE_BOARD_COMMENT);
        return ResponseEntity.ok(body);
    }
}
