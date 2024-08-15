package com.kr.lg.module.comment;

import com.kr.lg.common.utils.ClientUtils;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.model.annotation.UserAdapter;
import com.kr.lg.module.comment.model.req.EnrollCommentTrialRequest;
import com.kr.lg.module.comment.model.req.EnrollPositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentService;
import com.kr.lg.model.annotation.UserPrincipal;
import com.kr.lg.model.common.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentEnrollController {

    private final CommentService commentService;

    @ApiOperation(value = "포지션 게시판 댓글 작성하기", notes = "포지션 게시판 댓글 작성합니다.")
    @PostMapping("/position/comment/enroll")
    public ModelAndView positionCommentEnroll(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollPositionCommentRequest request,
            ModelAndView modelAndView,
            HttpServletRequest servletRequest
    ) throws CommentException {
        if (userTb == null) {
            commentService.enrollBoardCommentNotWithLogin(request, ClientUtils.getRemoteIP(servletRequest));
        } else {
            commentService.enrollBoardCommentWithLogin(request, userTb, ClientUtils.getRemoteIP(servletRequest));
        }

        modelAndView.setViewName("redirect:/position/" + request.getBoardId());
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "포지션 게시판 댓글 작성하기", notes = "포지션 게시판 댓글 작성합니다.")
    @PostMapping("/trial/comment/enroll")
    public ModelAndView trialCommentEnroll(
            @ApiParam(value = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            @Valid @ModelAttribute EnrollCommentTrialRequest request,
            ModelAndView modelAndView,
            HttpServletRequest servletRequest
    ) throws CommentException {
        commentService.enrollTrialCommentWithLogin(request, userTb, ClientUtils.getRemoteIP(servletRequest));

        modelAndView.setViewName("redirect:/trial/" + request.getTrialId());
        return modelAndView;
    }

}
