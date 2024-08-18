package com.kr.lg.module.comment;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.comment.model.req.DeleteTrialCommentRequest;
import com.kr.lg.module.comment.model.req.DeletePositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentDeleteController {

    private final CommentService commentService;

    @ApiOperation(value = "포지션 게시판 댓글 작성하기", notes = "포지션 게시판 댓글 작성합니다.")
    @PostMapping("/position/comment/delete")
    public ModelAndView positionCommentDelete(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeletePositionCommentRequest request,
            ModelAndView mav
    ) throws CommentException {
        if (userTb == null) {
            commentService.deleteBoardCommentNotWithLogin(request);
        } else {
            commentService.deleteBoardCommentWithLogin(request, userTb);
        }

        mav.setViewName("redirect:/position/" + request.getBoardId());
        return mav;
    }

    @Secured("ROLE_USER")
    @PostMapping("/trial/comment/delete")
    @ApiOperation(value = "포지션 게시판 댓글 작성하기", notes = "포지션 게시판 댓글 작성합니다.")
    public ModelAndView trialCommentDelete(
            @ApiParam(value = "로그인 세션 유저 정보", required = true) @AuthUser UserTb userTb,
            @Valid @ModelAttribute DeleteTrialCommentRequest request,
            ModelAndView mav
    ) throws CommentException {
        commentService.deleteTrialCommentWithLogin(request, userTb);
        mav.setViewName("redirect:/trial/" + request.getTrialId());
        return mav;
    }

}
