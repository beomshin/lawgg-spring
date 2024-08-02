package com.kr.lg.module.comment;

import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.comment.model.req.DeleteCommentTrialRequest;
import com.kr.lg.module.board.model.req.DeleteBoardCommentNotWithLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;
import com.kr.lg.model.annotation.UserPrincipal;
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


@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentDeleteController {

    private final CommentService commentService;

    @PostMapping("/api/public/v1/delete/board/comment")
    @ApiOperation(value = "비로그인 포지션 게시판 댓글 삭제", notes = "비로그인 포지션 게시판 댓글 삭제를합니다.")
    public ResponseEntity<?> deleteBoardCommentNotWithLogin(
            @RequestBody @Valid DeleteBoardCommentNotWithLoginRequest request
    ) throws CommentException { // 미사용 기능
        commentService.deleteBoardCommentNotWithLogin(request);
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/delete/board/comment")
    @ApiOperation(value = "로그인 포지션 게시판 댓글 삭제", notes = "로그인 포지션 게시판 댓글 삭제를합니다.")
    public ResponseEntity<?> deleteBoardCommentWithLogin(
            @RequestBody @Valid DeleteBoardCommentWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws CommentException { // 미사용 기능
        commentService.deleteBoardCommentWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/delete/trial/comment")
    @ApiOperation(value = "로그인 트라이얼 게시판 댓글 삭제", notes = "로그인 트라이얼 게시판 댓글 삭제를합니다.")
    public ResponseEntity<?> deleteCommentTrial(
            @RequestBody DeleteCommentTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws  CommentException {
        commentService.deleteTrialCommentWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }
}
