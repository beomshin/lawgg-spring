package com.kr.lg.controller.board.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.model.net.request.board.comment.DeleteACBRequest;
import com.kr.lg.model.net.request.board.comment.DeleteUCBRequest;
import com.kr.lg.service.board.comment.BoardCommentDeleteService;
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
public class BoardCommentDeleteController {

    private final BoardCommentDeleteService boardCommentDeleteService;

    @PostMapping("/api/public/board/delete/anonymous/comment")
    @ApiOperation(value = "익명 포지션 게시판 댓글 삭제", notes = "익명 포지션 게시판 댓글 삭제를합니다.")
    public ResponseEntity<DefaultResponse> deleteAnonymousCommentBoard(
            @RequestBody @Validated DeleteACBRequest request
    ) throws LgException {
        int result = boardCommentDeleteService.deleteAnonymousCommentBoard(new BoardLayer(request));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_COMMENT);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/api/board/delete/user/comment")
    @ApiOperation(value = "회원 포지션 게시판 댓글 삭제", notes = "회원 포지션 게시판 댓글 삭제를합니다.")
    public ResponseEntity<DefaultResponse> deleteUserCommentBoard(
            @RequestBody @Validated DeleteUCBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        int result = boardCommentDeleteService.deleteUserCommentBoard(new BoardLayer(request, userAdapter.getUserTb()));
        DefaultResponse body = result != 0 ? new DefaultResponse() : new DefaultResponse(GlobalCode.FAIL_DELETE_COMMENT);
        return ResponseEntity.ok(body);
    }
}
