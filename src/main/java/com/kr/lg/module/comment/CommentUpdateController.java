package com.kr.lg.module.comment;

import com.kr.lg.web.dto.annotation.UserAdapter;
import com.kr.lg.module.comment.model.req.ReportBoardCommentRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentWithLoginRequest;
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

import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentUpdateController {

    private final CommentService commentService;

    @PostMapping("/api/public/v1/update/board/comment")
    @ApiOperation(value = "비로그인 포지션 게시판 댓글 수정", notes = "비로그인 포지션 게시판 댓글 수정을 합니다.")
    public ResponseEntity<?> updateBoardCommentNotWithLogin(
            @RequestBody @Valid UpdateBoardCommentNotWithLoginRequest request
    ) throws CommentException { // 미사용 기능
        commentService.updateBoardCommentNotWithLogin(request);
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/v1/update/board/comment")
    @ApiOperation(value = "로그인 포지션 게시판 댓글 수정", notes = "로그인 포지션 게시판 댓글 수정을 합니다.")
    public ResponseEntity<?> updateBoardCommentWithLogin(
            @RequestBody @Valid UpdateBoardCommentWithLoginRequest request,
            @ApiParam(value = "회원 토큰", required = true) @UserPrincipal UserAdapter userAdapter
    ) throws CommentException { // 미사용 기능
        commentService.updateBoardCommentWithLogin(request, userAdapter.getUserTb());
        return ResponseEntity.ok().body(new SuccessResponse());
    }

    @PostMapping("/api/public/report/board/comment")
    @ApiOperation(value = "포지션 게시판 댓글 신고", notes = "포지션 게시판을 댓글 신고합니다.")
    public ResponseEntity<?> reportBoardComment(
            @RequestBody @Valid ReportBoardCommentRequest request
    ) throws CommentException { // 미사용  기능
        commentService.reportBoardComment(request);
        return ResponseEntity.ok().body(new SuccessResponse());
    }


}
