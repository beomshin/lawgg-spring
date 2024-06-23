package com.kr.lg.controller.board.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.UserAdapter;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.utils.ClientUtils;
import com.kr.lg.web.common.layer.BoardLayer;
import com.kr.lg.web.net.request.board.comment.EnrollACBRequest;
import com.kr.lg.web.net.request.board.comment.EnrollUCBRequest;
import com.kr.lg.service.board.comment.BoardCommentEnrollService;
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
public class BoardCommentEnrollController {

    private final BoardCommentEnrollService  boardCommentEnrollService;

    @PostMapping("/api/board/enroll/user/comment")
    @ApiOperation(value = "회원 포지션 게시판 댓글 등록", notes = "회원으로 포지션 게시판 댓글을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollUserCommentBoard(
            HttpServletRequest httpServletRequest,
            @Validated @RequestBody EnrollUCBRequest request,
            @ApiParam(value = "회원 토큰", required = true, hidden = true) @UserPrincipal UserAdapter userAdapter
    ) throws LgException {
        boardCommentEnrollService.enrollUserCommentBoard(new BoardLayer(request, userAdapter.getUserTb(), ClientUtils.getRemoteIP(httpServletRequest)));
        return ResponseEntity.ok(new DefaultResponse());
    }

    @PostMapping("/api/public/board/enroll/anonymous/comment")
    @ApiOperation(value = "익명 포지션 게시판 댓글 등록", notes = "익명으로 포지션 게시판 댓글을 등록합니다.")
    public ResponseEntity<DefaultResponse> enrollAnonymousCommentBoard(
            HttpServletRequest httpServletRequest,
            @Validated @RequestBody EnrollACBRequest request
    ) throws LgException {
        boardCommentEnrollService.enrollAnonymousCommentBoard(new BoardLayer(request, ClientUtils.getRemoteIP(httpServletRequest)));
        return ResponseEntity.ok(new DefaultResponse());
    }
}
