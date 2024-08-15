package com.kr.lg.module.comment;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.comment.model.req.DeleteCommentTrialRequest;
import com.kr.lg.module.comment.model.req.DeleteBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentDeleteController {

    private final CommentService commentService;

    @ApiOperation(value = "포지션 게시판 댓글 작성하기", notes = "포지션 게시판 댓글 작성합니다.")
    @PostMapping("/position/comment/delete")
    public ModelAndView positionCommentEnroll(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeleteBoardCommentNotWithLoginRequest request,
            ModelAndView modelAndView
    ) throws CommentException {
        if (userTb == null) {
            commentService.deleteBoardCommentNotWithLogin(request);
        } else {
            commentService.deleteBoardCommentWithLogin(request, userTb);
        }

        modelAndView.setViewName("redirect:/position/" + request.getBoardId());
        return modelAndView;
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
