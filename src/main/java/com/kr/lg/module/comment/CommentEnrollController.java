package com.kr.lg.module.comment;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.web.dto.annotation.UserAdapter;
import com.kr.lg.module.comment.model.req.EnrollCommentTrialRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;
import com.kr.lg.web.dto.annotation.UserPrincipal;
import com.kr.lg.web.dto.root.SuccessResponse;
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

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentEnrollController {

    private final CommentService commentService;

    @PostMapping("/api/public/v1/enroll/board/comment")
    @ApiOperation(value = "비로그인 포지션 게시판 댓글 등록", notes = "비로그인 포지션 게시판 댓글을 등록합니다.")
    public ResponseEntity<?> enrollBoardCommentNotWithLogin(
            HttpServletRequest servletRequest,
            @Valid @RequestBody EnrollBoardCommentNotWithLoginRequest request
    ) throws CommentException {
        commentService.enrollBoardCommentNotWithLogin(request, ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/enroll/board/comment")
    @ApiOperation(value = "로그인 포지션 게시판 댓글 등록", notes = "로그인 포지션 게시판 댓글을 등록합니다.")
    public ResponseEntity<?> enrollBoardCommentWithLogin(
            HttpServletRequest servletRequest,
            @Valid @RequestBody EnrollBoardCommentWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws CommentException {
        commentService.enrollBoardCommentWithLogin(request, userAdapter.getUserTb(), ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }

    @PostMapping("/api/v1/enroll/trial/comment")
    @ApiOperation(value = "로그인 트라이얼 게시판 댓글 등록", notes = "로그인 트라이얼 게시판 댓글을 등록합니다.")
    public ResponseEntity<?> enrollCommentTrial(
            HttpServletRequest servletRequest,
            @RequestBody @Valid EnrollCommentTrialRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws CommentException {
        commentService.enrollTrialCommentWithLogin(request,userAdapter.getUserTb(), ClientUtils.getRemoteIP(servletRequest));
        return ResponseEntity.ok(new SuccessResponse());
    }


}
